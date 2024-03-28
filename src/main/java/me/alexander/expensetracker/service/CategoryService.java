package me.alexander.expensetracker.service;

import me.alexander.expensetracker.model.dto.category.AddCategoryDTO;
import me.alexander.expensetracker.model.dto.category.CategoryDTO;

import java.util.List;

public interface CategoryService {

    void createCategory(AddCategoryDTO addCategoryDTO);

    List<CategoryDTO> getAllCategories();

}
