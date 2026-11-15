package com.proyecto.ventas_online.application.service;

import com.proyecto.ventas_online.application.ports.in.product.*;
import com.proyecto.ventas_online.application.ports.out.CartRepositoryPort;
import com.proyecto.ventas_online.application.ports.out.CategoryRepositoryPort;
import com.proyecto.ventas_online.application.ports.out.ProductRepositoryPort;
import com.proyecto.ventas_online.domain.exception.category.CategoryNotFoundException;
import com.proyecto.ventas_online.domain.exception.product.ProductNotFoundException;
import com.proyecto.ventas_online.domain.model.Cart;
import com.proyecto.ventas_online.domain.model.CartItem;
import com.proyecto.ventas_online.domain.model.Product;
import com.proyecto.ventas_online.infrastructure.adapter.out.persistence.InMemoryProductRepository;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
public class ProductService implements
        CreateProductUseCase,
        DeleteProductUseCase,
        GetAllProductsUseCase,
        GetProductUseCase,
        UpdateProductUseCase {

    private final ProductRepositoryPort repositoryProduct;
    private final CategoryRepositoryPort repositoryCategory;
    private final CartRepositoryPort cartRepositoryPort;

    @Override
    public Product createProduct(Product product) {
        if (repositoryCategory.findCategoryById(product.getIdCategory()).isEmpty()){
            throw new CategoryNotFoundException(product.getIdCategory());
        }
        return repositoryProduct.saveProduct(product);
    }

    @Override
    public void deteleProduct(UUID id) {
        if (repositoryProduct.findProductById(id).isEmpty()){
            throw new ProductNotFoundException(id);
        }
        //Borrar el producto de todos los carritos :v
        deleteProductsFromItemsCart(id);
        repositoryProduct.deleteProduct(id);
    }

    @Override
    public List<Product> getALl() {
        return repositoryProduct.getAll();
    }

    @Override
    public Product findProductById(UUID id) {
        return repositoryProduct.findProductById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    @Override
    public Product updateProduct(Product productToUpdate) {
        if (repositoryCategory.findCategoryById(productToUpdate.getIdCategory()).isEmpty()){
            throw new CategoryNotFoundException(productToUpdate.getIdCategory());
        }
        Product productInMemory = repositoryProduct.findProductById(productToUpdate.getId())
                .orElseThrow(() -> new ProductNotFoundException(productToUpdate.getId()));

        if (productInMemory.getPrice().compareTo(productToUpdate.getPrice()) != 0){
            updatePriceItemsCart(productToUpdate);
        }

        return repositoryProduct.updateProduct(productToUpdate);
    }

    private void updatePriceItemsCart(Product product){
        List<Cart> carts = cartRepositoryPort.findAll();
        carts.stream()
                .filter(cart -> cart.getItems().stream()
                        .anyMatch(cartItem -> cartItem.getIdProduct().equals(product.getId())))
                .forEach(cart -> {
                    cart.getItems().stream()
                            .filter(cartItem -> cartItem.getIdProduct().equals(product.getId()))
                            .findFirst()
                            .ifPresent(cartItem -> {
                                cartItem.setUnitPrice(product.getPrice());
                                cartRepositoryPort.saveCart(cart);
                            });
                });
    }

    private void deleteProductsFromItemsCart(UUID idProduct){
        List<Cart> carts = cartRepositoryPort.findAll();
        carts.stream()
                .filter(cart -> cart.getItems().stream()
                        .anyMatch(cartItem -> cartItem.getIdProduct().equals(idProduct)))
                .forEach(cart -> {
                    cart.getItems().removeIf(cartItem -> cartItem.getIdProduct().equals(idProduct));
                    cartRepositoryPort.saveCart(cart);
                });
    }
}
