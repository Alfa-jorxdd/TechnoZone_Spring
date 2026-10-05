package com.proyecto.ventas_online.infrastructure.ui.client.panels;

import com.proyecto.ventas_online.infrastructure.dto.product.ProductResponse;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class ProductCard extends JPanel {

    private static final Map<String, Icon> CACHE = new HashMap<>();

    public ProductCard(ProductResponse product, Consumer<ProductResponse> onAdd) {
        setLayout(new BorderLayout(0, 8));
        setPreferredSize(new Dimension(200, 290));
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 220)),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        setBackground(Color.WHITE);

        JLabel image = new JLabel(loadImage(product.imagePath()), SwingConstants.CENTER);
        JLabel name = new JLabel(product.name());
        JLabel price = new JLabel(String.format("S/ %,.2f", product.price()));
        price.setFont(price.getFont().deriveFont(Font.BOLD, 16f));

        JButton add = new JButton("Agregar al carrito");
        add.setEnabled(product.stock() > 0);
        if (product.stock() == 0) add.setText("Sin stock");
        add.addActionListener(e -> onAdd.accept(product));

        JPanel info = new JPanel(new GridLayout(0, 1, 0, 4));
        info.setOpaque(false);
        info.add(name);
        info.add(price);
        info.add(add);

        add(image, BorderLayout.CENTER);
        add(info, BorderLayout.SOUTH);
    }

    private Icon loadImage(String path) {
        if (path == null || path.isBlank()) return placeholder();
        return CACHE.computeIfAbsent(path, this::readAndScale);
    }

    private Icon readAndScale(String path) {
        try {
            URL url = getClass().getResource(path);
            if (url == null) return placeholder();
            BufferedImage src = ImageIO.read(url);
            if (src == null) return placeholder();

            BufferedImage dst = new BufferedImage(160, 160, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = dst.createGraphics();
            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                    RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g.drawImage(src, 0, 0, 160, 160, null);
            g.dispose();
            return new ImageIcon(dst);
        } catch (IOException e) {
            return placeholder();
        }
    }

    private Icon placeholder() {
        URL url = getClass().getResource("/images_small/image-not-found.png");
        if (url == null) return new ImageIcon();
        Image scaled = new ImageIcon(url).getImage()
                .getScaledInstance(160, 160, Image.SCALE_SMOOTH);
        return new ImageIcon(scaled);
    }
}
