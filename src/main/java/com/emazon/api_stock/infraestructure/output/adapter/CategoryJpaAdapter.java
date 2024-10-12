package com.emazon.api_stock.infraestructure.output.adapter;

import com.emazon.api_stock.domain.model.Category;
import com.emazon.api_stock.domain.model.Pagination;
import com.emazon.api_stock.domain.spi.ICategoryPersistencePort;
import com.emazon.api_stock.infraestructure.output.entity.CategoryEntity;
import com.emazon.api_stock.infraestructure.output.mapper.CategoryEntityMapper;
import com.emazon.api_stock.infraestructure.output.repository.ICategoryRepository;
import com.emazon.api_stock.infraestructure.utils.InfraestructureConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

@RequiredArgsConstructor
public class CategoryJpaAdapter implements ICategoryPersistencePort {

    private final ICategoryRepository categoryRepository;
    private final CategoryEntityMapper categoryEntityMapper;


    @Override
    public void saveCategory(Category category) {
        categoryRepository.save(categoryEntityMapper.categoryToCategoryEntity(category));
    }

    @Override
    public Pagination<Category> getAllCategories(Integer page, Integer size,boolean descending) {
        Pageable pagination = createPageable(page, size, descending);
        Page<CategoryEntity> categoryEntities = categoryRepository.findAll(pagination);
        List<Category> categoryList = categoryEntityMapper.categoryEntityToCategory(categoryEntities.getContent());

        return new Pagination<>(
                categoryList,
                categoryEntities.getTotalElements()
        );
    }

    @Override
    public boolean getCategoryByName(String name) {
        return categoryRepository.findByName(name).isPresent();
    }

    private Pageable createPageable(Integer page, Integer size, boolean descending) {
        Sort.Direction direction = descending ? Sort.Direction.DESC : Sort.Direction.ASC;
        return PageRequest.of(page, size, Sort.by(direction, InfraestructureConstants.NAME));
    }
}
