package com.proyecto.ventas_online.infrastructure.mappers;

import com.proyecto.ventas_online.domain.model.Category;
import com.proyecto.ventas_online.infrastructure.model.CategoryEntity;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {
    public CategoryEntity toEntity(Category category){
        if (category == null){
            return null;
        }
        return new CategoryEntity.CategoryEntityBuilder()
                .id(category.getId())
                .name(category.getName())
                .buildExisting();
    }

    public Category toDomain(CategoryEntity categoryEntity){
        if (categoryEntity == null) {
            return null;
        }
        return new Category.CategoryBuild()
                .id(categoryEntity.getId())
                .name(categoryEntity.getName())
                .buildExisting();
    }
}
