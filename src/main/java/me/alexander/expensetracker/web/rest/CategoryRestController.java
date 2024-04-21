package me.alexander.expensetracker.web.rest;

import jakarta.validation.Valid;
import me.alexander.expensetracker.model.dto.category.AddCategoryDTO;
import me.alexander.expensetracker.model.dto.category.CategoryDTO;
import me.alexander.expensetracker.model.dto.category.CategoryTransactionsDTO;
import me.alexander.expensetracker.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryRestController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryRestController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> categories() {
        List<CategoryDTO> categories = categoryService.getAllCategories();

        return ResponseEntity.ok(categories);
    }

    @GetMapping("/expenses")
    public ResponseEntity<List<CategoryTransactionsDTO>> categoriesExpenses() {
        List<CategoryTransactionsDTO> categories = categoryService.getCategoriesExpenses();

        return ResponseEntity.ok(categories);
    }

    @PostMapping("/add")
    public ResponseEntity<?> createCategory(@Valid @RequestBody AddCategoryDTO addCategoryDTO) {
        categoryService.createCategory(addCategoryDTO);

        return ResponseEntity.ok().build();
    }

}
