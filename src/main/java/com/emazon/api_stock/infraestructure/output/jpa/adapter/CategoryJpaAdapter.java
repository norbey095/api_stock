package com.emazon.api_stock.infraestructure.output.jpa.adapter;

import com.emazon.api_stock.domain.model.Category;
import com.emazon.api_stock.domain.spi.ICategoryPersistencePort;
import com.emazon.api_stock.infraestructure.exception.CategoryAlreadyExistsException;
import com.emazon.api_stock.infraestructure.exception.NegativeNotAllowedException;
import com.emazon.api_stock.infraestructure.exception.NoDataFoundException;
import com.emazon.api_stock.infraestructure.output.jpa.entity.CategoryEntity;
import com.emazon.api_stock.infraestructure.output.jpa.mapper.CategoryEntityMapper;
import com.emazon.api_stock.infraestructure.output.jpa.repository.ICategoryRepository;
import lombok.RequiredArgsConstructor;
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
        if(categoryRepository.findByName(category.getName()).isPresent()) {
            throw new CategoryAlreadyExistsException();
        }
        categoryRepository.save(categoryEntityMapper.categoryToCategoryEntity(category));
    }

    @Override
    public List<Category> getAllCategorys(Integer page, Integer size,boolean descending) {
        validateNegativeData(page,size);
        Pageable pagination = createPageable(page, size, descending);
        List<CategoryEntity> categoryEntities = fetchCategories(pagination);
        return categoryEntityMapper.categoryEntityToCategory(categoryEntities);
    }

    private void validateNegativeData(Integer page, Integer size){
        if (page < 0 || size < 0) {
            throw new NegativeNotAllowedException();
        }
    }

    private Pageable createPageable(Integer page, Integer size, boolean descending) {
        Sort.Direction direction = descending ? Sort.Direction.DESC : Sort.Direction.ASC;
        return PageRequest.of(page, size, Sort.by(direction, "name"));
    }

    private List<CategoryEntity> fetchCategories(Pageable pageable) {
        List<CategoryEntity> categories = categoryRepository.findAll(pageable).getContent();
        if (categories.isEmpty()) {
            throw new NoDataFoundException();
        }
        return categories;
    }
}
