package me.alexander.expensetracker.service;

import me.alexander.expensetracker.model.dto.category.AddCategoryDTO;
import me.alexander.expensetracker.model.dto.category.CategoryDTO;
import me.alexander.expensetracker.model.dto.category.CategoryTransactionsDTO;

import java.util.List;

public interface CategoryService {

    void createCategory(AddCategoryDTO addCategoryDTO);

    List<CategoryDTO> getAllCategories();

    List<CategoryTransactionsDTO> getCategoriesExpenses();

}
