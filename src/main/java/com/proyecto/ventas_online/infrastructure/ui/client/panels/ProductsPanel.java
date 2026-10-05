package com.proyecto.ventas_online.infrastructure.ui.client.panels;

import com.proyecto.ventas_online.domain.exception.DomainException;
import com.proyecto.ventas_online.infrastructure.controllers.api.CartApi;
import com.proyecto.ventas_online.infrastructure.controllers.api.ProductApi;
import com.proyecto.ventas_online.infrastructure.dto.cart.AddProductToCartRequest;
import com.proyecto.ventas_online.infrastructure.dto.product.ProductResponse;
import jakarta.validation.ConstraintViolationException;

import javax.swing.*;
import java.awt.*;

public class ProductsPanel extends JPanel implements Refreshable {

    private final ProductApi productApi;
    private final CartApi cartApi;
    private final JPanel grid = new JPanel(new GridLayout(0, 3, 16, 16));

    public ProductsPanel(ProductApi productApi, CartApi cartApi) {
        this.productApi = productApi;
        this.cartApi = cartApi;
        setLayout(new BorderLayout());

        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.add(grid, BorderLayout.NORTH);

        JScrollPane scroll = new JScrollPane(wrapper);
        scroll.setBorder(null);
        scroll.getVerticalScrollBar().setUnitIncrement(16);
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        add(scroll, BorderLayout.CENTER);
    }

    public void refresh() {
        grid.removeAll();
        for (ProductResponse p : productApi.findAll()) {
            grid.add(new ProductCard(p, this::addToCart));
        }
        grid.revalidate();
        grid.repaint();
    }

    private void addToCart(ProductResponse product) {
        try {
            cartApi.addProduct(new AddProductToCartRequest(product.id(), 1));
            JOptionPane.showMessageDialog(this, "Agregado: " + product.name(),
                    "Carrito", JOptionPane.INFORMATION_MESSAGE);
        } catch (DomainException | ConstraintViolationException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(),
                    "No se pudo agregar", JOptionPane.WARNING_MESSAGE);
        }
    }
}
