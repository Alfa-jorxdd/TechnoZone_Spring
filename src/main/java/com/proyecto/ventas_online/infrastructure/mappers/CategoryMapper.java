package com.proyecto.ventas_online.infrastructure.mappers;

import com.proyecto.ventas_online.domain.model.Category;
import com.proyecto.ventas_online.infrastructure.dto.category.CategoryResponse;
import com.proyecto.ventas_online.infrastructure.dto.category.CreateCategoryRequest;
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

    public Category toNewDomain(CreateCategoryRequest request) {
        return new Category.CategoryBuild()
                .name(request.name())
                .buildNew();
    }

    public CategoryResponse toResponse(Category category) {
        return new CategoryResponse(
                category.getId(),
                category.getName()
        );
    }
}
