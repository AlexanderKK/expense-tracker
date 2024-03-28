package me.alexander.expensetracker.service;

import me.alexander.expensetracker.model.dto.AddCategoryDTO;
import me.alexander.expensetracker.model.dto.CategoryDTO;

import java.util.List;

public interface CategoryService {

    void createCategory(AddCategoryDTO addCategoryDTO);

    List<CategoryDTO> getAllCategories();

}
