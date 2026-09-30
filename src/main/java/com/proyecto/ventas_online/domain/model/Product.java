package com.proyecto.ventas_online.domain.model;

import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@ToString
public class Product {
    private final UUID id;
    private final String name;
    private final String description;
    private final BigDecimal price;
    private final UUID idCategory;
    private final String imagePath;
    private final Integer stock;

    public Product(String name, String description, BigDecimal price, UUID idCategory, String imagePath, Integer stock) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.description = description;
        this.price = price;
        this.idCategory = idCategory;
        this.imagePath = imagePath;
        this.stock = stock;
    }

    private Product(ProductBuilder builder){
        this.id = builder.id;
        this.name = builder.name;
        this.description = builder.description;
        this.idCategory = builder.idCategory;
        this.price = builder.price;
        this.imagePath = builder.imagePath;
        this.stock = builder.stock;
    }

    public static class ProductBuilder{
        private UUID id;
        private String name;
        private String description;
        private BigDecimal price;
        private UUID idCategory;
        private String imagePath;
        private Integer stock;

        public ProductBuilder id(UUID id){
            this.id = id;
            return this;
        }

        public ProductBuilder name(String name){
            this.name = name;
            return this;
        }

        public ProductBuilder description(String description){
            this.description = description;
            return this;
        }

        public ProductBuilder price(BigDecimal price){
            this.price = price;
            return this;
        }

        public ProductBuilder idCategory(UUID idCategory){
            this.idCategory = idCategory;
            return this;
        }

        public ProductBuilder imagePath(String imagePath){
            this.imagePath = imagePath;
            return this;
        }

        public ProductBuilder stock(Integer stock){
            this.stock = stock;
            return this;
        }

        public Product buildNew(){
            this.id = UUID.randomUUID();
            return new Product(this);
        }

        public Product buildExisting(){
            if (this.id == null) {
                throw new IllegalStateException("id is requerid");
            }
            return new Product(this);
        }
    }
}
