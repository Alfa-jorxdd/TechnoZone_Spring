package com.proyecto.ventas_online.application.service.payment.status;

import com.proyecto.ventas_online.application.ports.in.payment.PaymentStatusUseCase;
import com.proyecto.ventas_online.application.ports.out.CartRepositoryPort;
import com.proyecto.ventas_online.application.ports.out.ProductRepositoryPort;
import com.proyecto.ventas_online.application.ports.out.PurchaseRepositoryPort;
import com.proyecto.ventas_online.domain.exception.cart.CartNotFoundException;
import com.proyecto.ventas_online.domain.exception.product.InsufficientStockException;
import com.proyecto.ventas_online.domain.exception.product.ProductNotFoundException;
import com.proyecto.ventas_online.domain.exception.user.UserNotFoundException;
import com.proyecto.ventas_online.domain.model.*;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RequiredArgsConstructor
public class CompletedPayment implements PaymentStatusUseCase {

    private final ProductRepositoryPort productRepository;
    private final PurchaseRepositoryPort purchaseRepository;
    private final CartRepositoryPort cartRepository;

    @Override
    public void handle(Purchase purchase) {
        discountStock(purchase);
        purchase.markPaid();
        purchaseRepository.save(purchase);
        clearAndSaveShoppingCart(purchase);
    }

    @Override
    public PaymentStatus getStatus() {
        return PaymentStatus.COMPLETED;
    }

    private void discountStock (Purchase purchase){
        Map<UUID, Product> productsToDiscount = getProductsToDiscount(purchase);
        purchase.getItems()
                .forEach(item ->{
                    Product product = productsToDiscount.get(item.idProduct());
                    product.decraseStock(item.quantity());
                    productRepository.updateProduct(product);
                });
    }

    private Map<UUID, Product> getProductsToDiscount(Purchase purchase){
        Map<UUID, Product> productsToDiscount = new HashMap<>();
        purchase.getItems()
                .forEach(purchaseItem -> {
                    Product product = productRepository.findProductById(purchaseItem.idProduct())
                            .orElseThrow(() -> new ProductNotFoundException(purchaseItem.idProduct()));
                    if (hasInsufficientStock(product, purchaseItem)) throw new InsufficientStockException();
                    productsToDiscount.put(product.getId(), product);
                });
        return productsToDiscount;
    }

    private boolean hasInsufficientStock(Product product, PurchaseItem item){
        return product.getStock() < item.quantity();
    }

    private Cart getShoppingCart(Purchase purchase){
        return cartRepository.findCartByUserId(purchase.getIdClient())
                .orElseThrow(CartNotFoundException::new);
    }

    private void clearAndSaveShoppingCart(Purchase purchase){
        Cart cart = getShoppingCart(purchase);
        cart.clearCart();
        cartRepository.saveCart(cart);
    }
}
