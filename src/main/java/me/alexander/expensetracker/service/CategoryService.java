package me.alexander.expensetracker.service;

import me.alexander.expensetracker.model.dto.AddCategoryDTO;

public interface CategoryService {

    void createCategory(AddCategoryDTO addCategoryDTO);

}
