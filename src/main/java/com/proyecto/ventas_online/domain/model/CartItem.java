package com.proyecto.ventas_online.domain.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@ToString
public class CartItem {
    private final UUID idProduct;
    private int quantity;
    private BigDecimal unitPrice;

    public CartItem(UUID idProduct, int quantity, BigDecimal unitPrice) {
        this.idProduct = idProduct;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public void increaseQuantity(int amount){
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
