package com.proyecto.ventas_online.infrastructure.adapter.out.persistence;

import com.proyecto.ventas_online.application.ports.out.CategoryRepositoryPort;
import com.proyecto.ventas_online.domain.factory.DefaultCategoryFactory;
import com.proyecto.ventas_online.domain.model.Category;
import com.proyecto.ventas_online.infrastructure.mappers.CategoryMapper;
import com.proyecto.ventas_online.infrastructure.model.CategoryEntity;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class InMemoryCategoryRepository implements CategoryRepositoryPort {

    private final CategoryMapper categoryMapper;
    private final Map<UUID, CategoryEntity> repositoryCategory = new HashMap<>();

    public InMemoryCategoryRepository(CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }

    @Override
    public Optional<Category> findCategoryById(UUID idCategory) {
        return Optional.ofNullable(repositoryCategory.get(idCategory))
                .map(categoryMapper::toDomain);
    }

    @Override
    public Category saveCategory(Category category) {
        CategoryEntity entityBySave = categoryMapper.toEntity(category);
        repositoryCategory.put(entityBySave.getId(), entityBySave);
        return categoryMapper.toDomain(entityBySave);
    }

    @Override
    public List<Category> getAll() {
        return repositoryCategory.values().stream()
                .map(categoryMapper::toDomain).toList();
    }
}