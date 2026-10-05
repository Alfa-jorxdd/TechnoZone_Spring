package com.proyecto.ventas_online.infrastructure.mappers;

import com.proyecto.ventas_online.domain.model.Product;
import com.proyecto.ventas_online.domain.model.User;
import com.proyecto.ventas_online.infrastructure.dto.product.CreateProductRequest;
import com.proyecto.ventas_online.infrastructure.dto.product.ProductResponse;
import com.proyecto.ventas_online.infrastructure.dto.product.UpdateProductRequest;
import com.proyecto.ventas_online.infrastructure.dto.user.CreateUserCommand;
import com.proyecto.ventas_online.infrastructure.model.ProductEntity;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public Product toDomain(ProductEntity productEntity){
        if (productEntity == null){
            return null;
        }

        return new Product.ProductBuilder()
                .id(productEntity.getId())
                .name(productEntity.getName())
                .description(productEntity.getDescription())
                .price(productEntity.getPrice())
                .stock(productEntity.getStock())
                .imagePath(productEntity.getImagePath())
                .idCategory(productEntity.getIdCategory())
                .buildExisting();
    }

    public Product toNewDomain(CreateProductRequest request) {
        return new Product.ProductBuilder()
                .name(request.name())
                .description(request.description())
                .price(request.price())
                .idCategory(request.idCategory())
                .imagePath(request.imagePath())
                .stock(request.stock())
                .buildNew();
    }

    public CreateUserCommand toCommand(User user){
        return new CreateUserCommand(
                user.getName(),
                user.getLastname(),
                user.getEmail(),
                user.getPasswordHash(),
                user.getRole()
        );
    }

    public ProductEntity toEntity(Product product){
        if (product == null){
            return null;
        }
        return new ProductEntity.ProductEntityBuilder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .stock(product.getStock())
                .imagePath(product.getImagePath())
                .idCategory(product.getIdCategory())
                .build();
    }

    public Product toExistingDomain(UpdateProductRequest request) {
        return new Product.ProductBuilder()
                .id(request.id())
                .name(request.name())
                .description(request.description())
                .price(request.price())
                .idCategory(request.idCategory())
                .imagePath(request.imagePath())
                .stock(request.stock())
                .buildExisting();
    }

    public ProductResponse toResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getIdCategory(),
                product.getImagePath(),
                product.getStock()
        );
    }
}
