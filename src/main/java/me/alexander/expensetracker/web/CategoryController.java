package me.alexander.expensetracker.web;

import jakarta.validation.Valid;
import me.alexander.expensetracker.model.dto.AddCategoryDTO;
import me.alexander.expensetracker.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/add")
    public ResponseEntity<AddCategoryDTO> addCategory(@Valid @RequestBody AddCategoryDTO addCategoryDTO) {
        categoryService.createCategory(addCategoryDTO);

        return ResponseEntity.ok().build();
    }

}
