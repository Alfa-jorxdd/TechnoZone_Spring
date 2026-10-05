package com.proyecto.ventas_online.infrastructure.ui.client.panels;

import com.proyecto.ventas_online.infrastructure.dto.cart.CartItemResponse;
import com.proyecto.ventas_online.infrastructure.dto.product.ProductResponse;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Consumer;

public class CartItemRow extends JPanel {

    private static final int IMG = 80;
    private static final Map<String, Icon> CACHE = new HashMap<>();

    public CartItemRow(CartItemResponse response,
                         ProductResponse product,
                         Consumer<UUID> onAdd,
                         Consumer<UUID> onRemove,
                         Consumer<UUID> onDelete) {

        setLayout(new BorderLayout(12, 0));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230)),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        setPreferredSize(new Dimension(0, 110));
        setMaximumSize(new Dimension(Integer.MAX_VALUE, 110));
        setAlignmentX(Component.LEFT_ALIGNMENT);


        String path = product != null ? product.imagePath() : null;
        add(new JLabel(loadImage(path)), BorderLayout.WEST);


        String name = product != null ? product.name() : "Producto no disponible";
        JLabel nameLabel = new JLabel(name);
        nameLabel.setFont(nameLabel.getFont().deriveFont(Font.BOLD, 14f));
        JLabel unitLabel = new JLabel(String.format("Precio: S/ %,.2f", response.unitPrice()));
        JLabel subtotalLabel = new JLabel(String.format("Subtotal: S/ %,.2f", response.subtotal()));
        subtotalLabel.setFont(subtotalLabel.getFont().deriveFont(Font.BOLD));

        JPanel info = new JPanel(new GridLayout(0, 1, 0, 4));
        info.setOpaque(false);
        info.add(nameLabel);
        info.add(unitLabel);
        info.add(subtotalLabel);
        add(info, BorderLayout.CENTER);

        //botones papu
        JButton minus = new JButton("-");
        JButton plus = new JButton("+");
        JButton delete = new JButton("Eliminar");
        JLabel qty = new JLabel(String.valueOf(response.quantity()), SwingConstants.CENTER);
        qty.setPreferredSize(new Dimension(30, 20));

        UUID id = response.idProduct();
        minus.addActionListener(e -> onRemove.accept(id));
        plus.addActionListener(e -> onAdd.accept(id));
        delete.addActionListener(e -> onDelete.accept(id));

        if (product != null && response.quantity() >= product.stock()) {
            plus.setEnabled(false);
        }
        if (product == null) {
            plus.setEnabled(false);
        }

        JPanel quantityBox = new JPanel(new FlowLayout(FlowLayout.CENTER, 4, 0));
        quantityBox.setOpaque(false);
        quantityBox.add(minus);
        quantityBox.add(qty);
        quantityBox.add(plus);

        JPanel controls = new JPanel(new GridLayout(0, 1, 0, 6));
        controls.setOpaque(false);
        controls.add(quantityBox);
        controls.add(delete);
        add(controls, BorderLayout.EAST);
    }

    private Icon loadImage(String path) {
        String key = (path == null || path.isBlank()) ? "/images/image-not-found.png" : path;
        return CACHE.computeIfAbsent(key, this::readAndScale);
    }

    private Icon readAndScale(String path) {
        try {
            URL url = getClass().getResource(path);
            if (url == null) url = getClass().getResource("/images/image-not-found.png");
            if (url == null) return new ImageIcon();
            BufferedImage src = ImageIO.read(url);
            if (src == null) return new ImageIcon();

            BufferedImage dst = new BufferedImage(IMG, IMG, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = dst.createGraphics();
            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                    RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g.drawImage(src, 0, 0, IMG, IMG, null);
            g.dispose();
            return new ImageIcon(dst);
        } catch (IOException e) {
            return new ImageIcon();
        }
    }
}
