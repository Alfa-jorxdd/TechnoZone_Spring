package com.proyecto.ventas_online.infrastructure.ui.client;

import com.proyecto.ventas_online.domain.model.User;
import com.proyecto.ventas_online.infrastructure.controllers.api.*;
import com.proyecto.ventas_online.infrastructure.dto.user.UserResponse;
import com.proyecto.ventas_online.infrastructure.ui.WindowUser;
import com.proyecto.ventas_online.infrastructure.ui.client.panels.CartPanel;
import com.proyecto.ventas_online.infrastructure.ui.client.panels.ProductsPanel;
import com.proyecto.ventas_online.infrastructure.ui.client.panels.Refreshable;

import java.awt.*;

public class WindowClient extends javax.swing.JFrame implements WindowUser{
    
    private final Runnable onLogout;
    private final ProductApi productApi;
    private final CategoryApi categoryApi;
    private final CartApi cartApi;

    private final CardLayout cards = new CardLayout();
    private ProductsPanel productsPanel;
    private CartPanel cartPanel;

    public WindowClient(Runnable onLogout, ProductApi productApi, CategoryApi categoryApi, CartApi cartApi, UserApi userApi, AuthApi authApi) {
        this.productApi = productApi;
        this.categoryApi = categoryApi;
        this.cartApi = cartApi;
        this.onLogout = onLogout;
        initComponents();

        contentPanel.setPreferredSize(new Dimension(710, 524));
        contentPanel.setLayout(cards);
        productsPanel = new ProductsPanel(productApi, cartApi);
        cartPanel = new CartPanel(productApi, cartApi);
        
        contentPanel.add(productsPanel, "products");
        contentPanel.add(cartPanel, "cart");
        
        UserResponse client = userApi.findById(authApi.getCurrentSession().userId());
        
        labelNameClient.setText("Bienvenido " + client.name() + " " + client.lastname());
        
        showPanel(productsPanel ,"products");
    }

    private void showPanel(Refreshable panelRefreshable, String name) {
        panelRefreshable.refresh();
        cards.show(contentPanel, name);
    }

    @Override
    public void display() {
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void close() {
        dispose();
        if (onLogout != null) {
            onLogout.run();
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bgMain = new javax.swing.JPanel();
        contentPanel = new javax.swing.JPanel();
        bgOptions = new javax.swing.JPanel();
        btnInicio = new javax.swing.JButton();
        btnCarrito = new javax.swing.JButton();
        btnCompras = new javax.swing.JButton();
        btnLogout = new javax.swing.JButton();
        labelNameClient = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        bgMain.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout contentPanelLayout = new javax.swing.GroupLayout(contentPanel);
        contentPanel.setLayout(contentPanelLayout);
        contentPanelLayout.setHorizontalGroup(
            contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 710, Short.MAX_VALUE)
        );
        contentPanelLayout.setVerticalGroup(
            contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 524, Short.MAX_VALUE)
        );

        bgOptions.setBackground(new java.awt.Color(51, 102, 255));

        btnInicio.setText("Inicio");
        btnInicio.addActionListener(this::btnInicioActionPerformed);

        btnCarrito.setText("Carrito");
        btnCarrito.addActionListener(this::btnCarritoActionPerformed);

        btnCompras.setText("Compras");
        btnCompras.setFocusable(false);

        btnLogout.setText("Cerrar sesión");
        btnLogout.addActionListener(this::btnLogoutActionPerformed);

        javax.swing.GroupLayout bgOptionsLayout = new javax.swing.GroupLayout(bgOptions);
        bgOptions.setLayout(bgOptionsLayout);
        bgOptionsLayout.setHorizontalGroup(
            bgOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnInicio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnCarrito, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnCompras, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnLogout, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
        );
        bgOptionsLayout.setVerticalGroup(
            bgOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgOptionsLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(btnInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCarrito, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCompras, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 115, Short.MAX_VALUE)
                .addComponent(btnLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        labelNameClient.setText("jLabel1");

        javax.swing.GroupLayout bgMainLayout = new javax.swing.GroupLayout(bgMain);
        bgMain.setLayout(bgMainLayout);
        bgMainLayout.setHorizontalGroup(
            bgMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgMainLayout.createSequentialGroup()
                .addGroup(bgMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(bgMainLayout.createSequentialGroup()
                        .addComponent(bgOptions, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(56, 56, 56)
                        .addComponent(contentPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(bgMainLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(labelNameClient, javax.swing.GroupLayout.PREFERRED_SIZE, 668, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        bgMainLayout.setVerticalGroup(
            bgMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgMainLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelNameClient)
                .addGroup(bgMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(bgMainLayout.createSequentialGroup()
                        .addGap(235, 235, 235)
                        .addComponent(bgOptions, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(bgMainLayout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(contentPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bgMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bgMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        close();
    }//GEN-LAST:event_formWindowClosing

    private void btnInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInicioActionPerformed
        showPanel(productsPanel, "products");
    }//GEN-LAST:event_btnInicioActionPerformed

    private void btnCarritoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCarritoActionPerformed
        showPanel(cartPanel, "cart");
    }//GEN-LAST:event_btnCarritoActionPerformed

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        close();
    }//GEN-LAST:event_btnLogoutActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bgMain;
    private javax.swing.JPanel bgOptions;
    private javax.swing.JButton btnCarrito;
    private javax.swing.JButton btnCompras;
    private javax.swing.JButton btnInicio;
    private javax.swing.JButton btnLogout;
    private javax.swing.JPanel contentPanel;
    private javax.swing.JLabel labelNameClient;
    // End of variables declaration//GEN-END:variables
}
