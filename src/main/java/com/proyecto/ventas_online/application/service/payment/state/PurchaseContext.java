package com.proyecto.ventas_online.application.service.payment.state;

import com.proyecto.ventas_online.application.ports.out.CartRepositoryPort;
import com.proyecto.ventas_online.application.ports.out.ProductRepositoryPort;
import com.proyecto.ventas_online.application.ports.out.PurchaseRepositoryPort;
import com.proyecto.ventas_online.domain.exception.cart.CartNotFoundException;
import com.proyecto.ventas_online.domain.exception.product.InsufficientStockException;
import com.proyecto.ventas_online.domain.exception.product.ProductNotFoundException;
import com.proyecto.ventas_online.domain.model.Cart;
import com.proyecto.ventas_online.domain.model.Product;
import com.proyecto.ventas_online.domain.model.Purchase;
import com.proyecto.ventas_online.domain.model.PurchaseItem;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PurchaseContext {

    private final Purchase purchase;
    private PurchaseState state;
    private final PurchaseRepositoryPort purchaseRepository;
    private final ProductRepositoryPort productRepository;
    private final CartRepositoryPort cartRepository;

    PurchaseContext(Purchase purchase, PurchaseState state,
                    PurchaseRepositoryPort purchaseRepository,
                    ProductRepositoryPort productRepository,
                    CartRepositoryPort cartRepository) {
        this.purchase = purchase;
        this.state = state;
        this.purchaseRepository = purchaseRepository;
        this.productRepository = productRepository;
        this.cartRepository = cartRepository;
    }

    public void complete()          { state.complete(this); }
    public void fail(String reason) { state.fail(this, reason); }
    public void cancel()            { state.cancel(this); }
    public boolean canConfirm()     { return state.canConfirm(); }

    void moveTo(PurchaseState next) {
        this.state = next;
        purchase.changeStatus(next.status());
        purchaseRepository.save(purchase);
    }

    void discountStock() {
        Map<UUID, Product> products = new HashMap<>();
        for (PurchaseItem item : purchase.getItems()) {
            Product product = productRepository.findProductById(item.idProduct())
                    .orElseThrow(() -> new ProductNotFoundException(item.idProduct()));
            if (product.getStock() < item.quantity()) throw new InsufficientStockException();
            products.put(product.getId(), product);
        }
        purchase.getItems().forEach(item -> {
            Product product = products.get(item.idProduct());
            product.decraseStock(item.quantity());
            productRepository.updateProduct(product);
        });
    }

    void clearCart() {
        Cart cart = cartRepository.findCartByUserId(purchase.getIdClient())
                .orElseThrow(CartNotFoundException::new);
        cart.clearCart();
        cartRepository.saveCart(cart);
    }
}
