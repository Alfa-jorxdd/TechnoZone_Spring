package com.proyecto.ventas_online.infrastructure.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@ToString
@RequiredArgsConstructor
public class ProductEntity {
    private final UUID id;
    private final String name;
    private final String description;
    private final BigDecimal price;
    private final UUID idCategory;
    private final String imagePath;
    private final Integer stock;

    private ProductEntity(ProductEntityBuilder builder){
        this.id = builder.id;
        this.name = builder.name;
        this.description = builder.description;
        this.idCategory = builder.idCategory;
        this.price = builder.price;
        this.imagePath = builder.imagePath;
        this.stock = builder.stock;
    }

    public static class ProductEntityBuilder{
        private UUID id;
        private String name;
        private String description;
        private BigDecimal price;
        private UUID idCategory;
        private String imagePath;
        private Integer stock;

        public ProductEntityBuilder id(UUID id){
            this.id = id;
            return this;
        }

        public ProductEntityBuilder name(String name){
            this.name = name;
            return this;
        }

        public ProductEntityBuilder description(String description){
            this.description = description;
            return this;
        }

        public ProductEntityBuilder price(BigDecimal price){
            this.price = price;
            return this;
        }

        public ProductEntityBuilder idCategory(UUID idCategory){
            this.idCategory = idCategory;
            return this;
        }

        public ProductEntityBuilder imagePath(String imagePath){
            this.imagePath = imagePath;
            return this;
        }

        public ProductEntityBuilder stock(Integer stock){
            this.stock = stock;
            return this;
        }

        public ProductEntity build(){
            return new ProductEntity(this);
        }
    }
}
