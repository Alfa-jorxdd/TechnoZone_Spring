package com.proyecto.ventas_online.domain.model;

import lombok.Getter;
import lombok.ToString;

import java.util.UUID;

@Getter
@ToString
public class Category {
    private final UUID id;
    private final String name;

    public Category(String name) {
        this.id = UUID.randomUUID();
        this.name = name;
    }

    private Category(CategoryBuild builder){
        this.id = builder.id;
        this.name = builder.name;
    }

    public static class CategoryBuild {
        private UUID id;
        private String name;

        public CategoryBuild id(UUID id){
            this.id = id;
            return this;
        }

        public CategoryBuild name(String name){
            this.name = name;
            return this;
        }

        public Category buildNew(){
            this.id = UUID.randomUUID();
            return new Category(this);
        }

        public Category buildExisting(){
            if (id == null){
                throw new IllegalStateException("id is requerid");
            }
            return new Category(this);
        }
    }
}
