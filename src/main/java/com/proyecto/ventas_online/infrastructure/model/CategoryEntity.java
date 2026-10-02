package com.proyecto.ventas_online.infrastructure.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.UUID;

@Getter
@ToString
public class CategoryEntity {
    private final UUID id;
    private final String name;

    private CategoryEntity(CategoryEntityBuilder builder){
        this.id = builder.id;
        this.name = builder.name;
    }

    public static class CategoryEntityBuilder{
        private UUID id;
        private String name;

        public CategoryEntityBuilder id(UUID id){
            this.id = id;
            return this;
        }

        public CategoryEntityBuilder name(String name){
            this.name = name;
            return this;
        }

        public CategoryEntity buildNew(){
            this.id = UUID.randomUUID();
            return new CategoryEntity(this);
        }

        public CategoryEntity buildExisting(){
            if (id == null){
                throw new IllegalStateException("id is requerid");
            }
            return new CategoryEntity(this);
        }
    }
}
