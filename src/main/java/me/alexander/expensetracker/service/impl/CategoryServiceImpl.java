package me.alexander.expensetracker.service.impl;

import me.alexander.expensetracker.model.dto.category.AddCategoryDTO;
import me.alexander.expensetracker.model.dto.category.CategoryDTO;
import me.alexander.expensetracker.model.entity.Category;
import me.alexander.expensetracker.repository.CategoryRepository;
import me.alexander.expensetracker.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final ModelMapper mapper;
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(ModelMapper mapper,
                               CategoryRepository categoryRepository) {
        this.mapper = mapper;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void createCategory(AddCategoryDTO addCategoryDTO) {
        Category category = mapper.map(addCategoryDTO, Category.class);

        categoryRepository.save(category);
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        return categoryRepository.findAll().stream()
                .map(category -> mapper.map(category, CategoryDTO.class))
                .toList();
    }

}
