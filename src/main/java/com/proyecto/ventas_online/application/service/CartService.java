package com.proyecto.ventas_online.application.service;

import com.proyecto.ventas_online.application.ports.in.cart.*;
import com.proyecto.ventas_online.application.ports.out.CartRepositoryPort;
import com.proyecto.ventas_online.application.ports.out.ProductRepositoryPort;
import com.proyecto.ventas_online.application.ports.out.SessionContext;
import com.proyecto.ventas_online.application.ports.out.UserRepositoryPort;
import com.proyecto.ventas_online.domain.exception.cart.CartNotFoundException;
import com.proyecto.ventas_online.domain.exception.cart.DuplicateCartException;
import com.proyecto.ventas_online.domain.exception.cart.InvalidQuantityException;
import com.proyecto.ventas_online.domain.exception.product.InsufficientStockException;
import com.proyecto.ventas_online.domain.exception.product.ProductNotFoundException;
import com.proyecto.ventas_online.domain.exception.user.UserNotFoundException;
import com.proyecto.ventas_online.domain.model.Cart;
import com.proyecto.ventas_online.domain.model.CartItem;
import com.proyecto.ventas_online.domain.model.Product;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
public class CartService implements
        AddProductUseCase,
        ClearCartUseCase,
        CreateCartUseCase,
        DeleteCartByUserUseCase,
        DeleteItemCartUseCase,
        GetCartByUserUseCase,
        GetCartUseCase,
        CalculateTotalUseCase,
        CalculateSubTotalUseCase,
        DecreaseProductQuantityUseCase{

    private final CartRepositoryPort cartRepository;
    private final ProductRepositoryPort productRepository;
    private final UserRepositoryPort userRepository;

    private final SessionContext sessionContext;

    @Override
    public Cart addProduct(UUID idProduct, int quantityProduct) {
        Product product = productRepository.findProductById(idProduct)
                .orElseThrow(() -> new ProductNotFoundException(idProduct));

        if (quantityProduct <= 0){
            throw new InvalidQuantityException();
        }

        UUID idUser = sessionContext.getCurrentUserId();
        Cart cartToSave = cartRepository.findCartByUserId(idUser)
                .orElseGet(this::createCart);

        int quantityCartItem = cartToSave.getItems().stream()
                .filter(item -> item.getIdProduct().equals(idProduct))
                .findFirst()
                .map(CartItem::getQuantity).orElse(0);

        if (quantityCartItem + quantityProduct > product.getStock()) {
            throw new InsufficientStockException();
        }

        cartToSave.getItems().stream()
                .filter(item -> item.getIdProduct().equals(idProduct))
                .findFirst()
                .ifPresentOrElse(
                        item -> item.increaseQuantity(quantityProduct)
                        ,
                        () -> cartToSave.addProduct(idProduct, quantityProduct, product.getPrice())
                );
        return cartRepository.saveCart(cartToSave);
    }

    @Override
    public Cart clearCart() {
        UUID idUser = sessionContext.getCurrentUserId();
        Cart cartToClear = cartRepository.findCartByUserId(idUser)
                .orElseThrow(UserNotFoundException::new);
        cartToClear.clearCart();
        return cartRepository.saveCart(cartToClear);
    }

    @Override
    public Cart createCart() {
        UUID idUser = sessionContext.getCurrentUserId();
        if (cartRepository.findCartByUserId(idUser).isPresent()){
            throw new DuplicateCartException(idUser);
        }
        userRepository.findUserById(idUser)
                .orElseThrow(UserNotFoundException::new);
        Cart cartToSave = new Cart(idUser);
        return cartRepository.saveCart(cartToSave);
    }

    @Override
    public Cart deleteItemCart(UUID idProduct) {
        productRepository.findProductById(idProduct)
                .orElseThrow(() -> new ProductNotFoundException(idProduct));

        UUID idUser = sessionContext.getCurrentUserId();
        Cart cart = cartRepository.findCartByUserId(idUser)
                .orElseThrow(UserNotFoundException::new);

        cart.deleteProduct(idProduct);
        return cartRepository.saveCart(cart);
    }

    @Override
    public void deleteCartByUserId() {
        UUID idUser = sessionContext.getCurrentUserId();
        if (cartRepository.findCartByUserId(idUser).isEmpty()){
            throw new CartNotFoundException();
        }
        cartRepository.deleteCartByUserId(idUser);
    }

    @Override
    public Cart findCartByUserId() {
        UUID idUser = sessionContext.getCurrentUserId();
        return cartRepository.findCartByUserId(idUser)
                .orElseThrow(UserNotFoundException::new);
    }

    @Override
    public Cart findById(UUID id) {
        return cartRepository.findCartById(id)
                .orElseThrow(CartNotFoundException::new);
    }

    @Override
    public BigDecimal calculateTotal() {
        UUID idUser = sessionContext.getCurrentUserId();
        Cart cart = cartRepository.findCartByUserId(idUser)
                .orElseThrow(CartNotFoundException::new);
        return cart.calculateTotal();
    }

    @Override
    public List<BigDecimal> calculateSubTotal() {
        UUID idUser = sessionContext.getCurrentUserId();
        Cart cart = cartRepository.findCartByUserId(idUser)
                .orElseThrow(CartNotFoundException::new);

        return cart.getItems().stream()
                .map(CartItem::subtotal)
                .toList();
    }

    @Override
    public Cart decreaseProduct(UUID idProduct, int quantity) {
        if (quantity <= 0) throw new InvalidQuantityException();

        Cart cart = cartRepository.findCartByUserId(sessionContext.getCurrentUserId())
                .orElseThrow(CartNotFoundException::new);

        cart.decreaseProduct(idProduct, quantity);
        return cartRepository.saveCart(cart);
    }
}
