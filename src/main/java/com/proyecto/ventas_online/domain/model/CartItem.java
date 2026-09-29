package com.proyecto.ventas_online.domain.model;

import com.proyecto.ventas_online.domain.exception.InvalidQuantityException;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class CartItem {
    private final UUID idProduct;
    private int quantity;
    private final BigDecimal unitPrice;

    public CartItem(UUID idProduct, int quantity, BigDecimal unitPrice) {
        if (quantity <= 0) throw new InvalidQuantityException("The quantity must be greater than 0");
        this.idProduct = UUID.randomUUID();
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public void increaseQuantity(int amount){
        if (amount <= 0) throw new InvalidQuantityException("The amount must be greater than 0");
        this.quantity += amount;
    }

    public void decreaseQuantity(int amount){
        if (amount >= quantity) {
            this.quantity = 0;
            return;
        }
        this.quantity -= amount;
    }

    public BigDecimal subtotal(){
        return unitPrice.multiply(BigDecimal.valueOf(quantity));
    }
}
