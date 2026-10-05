package com.proyecto.ventas_online.infrastructure.ui.client.panels;

import com.proyecto.ventas_online.domain.exception.DomainException;
import com.proyecto.ventas_online.infrastructure.controllers.api.CartApi;
import com.proyecto.ventas_online.infrastructure.controllers.api.ProductApi;
import com.proyecto.ventas_online.infrastructure.dto.cart.AddProductToCartRequest;
import com.proyecto.ventas_online.infrastructure.dto.cart.CartItemResponse;
import com.proyecto.ventas_online.infrastructure.dto.cart.CartResponse;
import com.proyecto.ventas_online.infrastructure.dto.cart.DecreaseProductRequest;
import com.proyecto.ventas_online.infrastructure.dto.product.ProductResponse;
import jakarta.validation.ConstraintViolationException;

import javax.swing.*;
import java.awt.*;
import java.util.UUID;

public class CartPanel extends JPanel implements Refreshable {
    private final ProductApi productApi;
    private final CartApi cartApi;
    private final JPanel grid = new JPanel();
    private final JLabel totalLabel = new JLabel("Total: S/ 0.00");

    public CartPanel(ProductApi productApi, CartApi cartApi) {
        this.productApi = productApi;
        this.cartApi = cartApi;
        setLayout(new BorderLayout());

        grid.setLayout(new BoxLayout(grid, BoxLayout.Y_AXIS));

        JScrollPane scroll = new JScrollPane(grid);
        scroll.setBorder(null);
        scroll.getVerticalScrollBar().setUnitIncrement(16);
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        add(scroll, BorderLayout.CENTER);

        totalLabel.setFont(totalLabel.getFont().deriveFont(Font.BOLD, 20f));
        JPanel totalPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 16, 12));
        totalPanel.setBackground(Color.WHITE);
        totalPanel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(220, 220, 220)));
        totalPanel.add(totalLabel);
        add(totalPanel, BorderLayout.SOUTH);
    }

    @Override
    public void refresh() {
        grid.removeAll();
        try {
            CartResponse cart = cartApi.getMyCart();

            for (CartItemResponse item : cart.items()) {
                grid.add(new CartItemRow(item, findProduct(item.idProduct()),
                        id -> perform(() -> cartApi.addProduct(new AddProductToCartRequest(id, 1))),
                        id -> perform(() -> cartApi.decreaseProduct(new DecreaseProductRequest(id, 1))),
                        id -> perform(() -> cartApi.removeProduct(id))));
            }
            if (cart.items().isEmpty()) {
                grid.add(emptyMessage());
            }
            totalLabel.setText(String.format("Total: S/ %,.2f", cart.total()));
        } catch (DomainException e) {
            grid.add(emptyMessage());
            totalLabel.setText("Total: S/ 0.00");
        }
        grid.add(Box.createVerticalGlue());
        grid.revalidate();
        grid.repaint();
    }

    private ProductResponse findProduct(UUID idProduct) {
        try {
            return productApi.findById(idProduct);
        } catch (DomainException e) {
            return null;   // producto eliminado del catálogo
        }
    }

    private JLabel emptyMessage() {
        JLabel label = new JLabel("Tu carrito está vacío", SwingConstants.CENTER);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setBorder(BorderFactory.createEmptyBorder(40, 0, 0, 0));
        return label;
    }

    // Ejecuta una acción del carrito, muestra el error si falla y siempre repinta
    private void perform(Runnable action) {
        try {
            action.run();
        } catch (DomainException | ConstraintViolationException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(),
                    "No se pudo actualizar el carrito", JOptionPane.WARNING_MESSAGE);
        }
        refresh();
    }
}
