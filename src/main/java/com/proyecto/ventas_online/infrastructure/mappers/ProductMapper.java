package com.proyecto.ventas_online.infrastructure.mappers;

import com.proyecto.ventas_online.domain.model.Product;
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
}
