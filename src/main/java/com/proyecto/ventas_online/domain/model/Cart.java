package com.proyecto.ventas_online.domain.model;

import com.proyecto.ventas_online.domain.exception.cart.ProductNotInCartException;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;

@Getter
@ToString
public class Cart {
    private final UUID idCart;
    private final UUID idClient;
    private final List<CartItem> items;

    public Cart(UUID idClient) {
        this.idCart = UUID.randomUUID();
        this.idClient = idClient;
        this.items = new ArrayList<>();
    }

    public void addProduct(UUID idProduct, int quantity, BigDecimal unitPrice){
        CartItem newItem = new CartItem(idProduct, quantity, unitPrice);
        this.items.add(newItem);
    }

    public void decreaseProduct(UUID idProduct, int amount) {
        CartItem item = items.stream()
                .filter(i -> i.getIdProduct().equals(idProduct))
                .findFirst()
                .orElseThrow(() -> new ProductNotInCartException(idProduct));

        item.decreaseQuantity(amount);
        if (item.getQuantity() == 0) {
            items.remove(item);
        }
    }

    public void deleteProduct(UUID idProduct){
        int indexProductByDelete = IntStream.range(0, items.size())
                .filter(i -> idProduct.equals(items.get(i).getIdProduct()))
                .findFirst()
                .orElse(-1);
        if (indexProductByDelete == -1) return;
        this.items.remove(indexProductByDelete);
    }

    public BigDecimal calculateTotal(){
        return this.items.stream()
                .map(CartItem::subtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void clearCart(){
        this.items.clear();
    }

    private Cart(CartBuilder builder){
        this.idCart = builder.idCart;
        this.idClient = builder.idClient;
        this.items = builder.items;
    }

    public static class CartBuilder{
        private UUID idCart;
        private UUID idClient;
        private List<CartItem> items;

        public CartBuilder idCart(UUID idCart){
            this.idCart = idCart;
            return this;
        }

        public CartBuilder idClient(UUID idClient){
            this.idClient = idClient;
            return this;
        }

        public CartBuilder items(List<CartItem> items){
            this.items = items;
            return this;
        }

        public Cart buildNew(){
            this.idCart = UUID.randomUUID();
            return new Cart(this);
        }

        public Cart buildExisting(){
            if (idCart == null){
                return null;
            }
            return new Cart(this);
        }
    }
}
