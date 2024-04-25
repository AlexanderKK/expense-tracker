package me.alexander.expensetracker.web.rest;

import me.alexander.expensetracker.model.dto.YearlyIncomeExpensesDTO;
import me.alexander.expensetracker.model.dto.category.CategoryTransactionsDTO;
import me.alexander.expensetracker.service.CategoryService;
import me.alexander.expensetracker.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/home")
public class HomeRestController {

    private final CategoryService categoryService;
    private final HomeService homeService;

    @Autowired
    public HomeRestController(CategoryService categoryService,
                              HomeService homeService) {
        this.categoryService = categoryService;
        this.homeService = homeService;
    }

    @GetMapping("/expenses-by-categories")
    public ResponseEntity<List<CategoryTransactionsDTO>> categoriesExpenses() {
        List<CategoryTransactionsDTO> categories = categoryService.getCategoriesExpenses();

        return ResponseEntity.ok(categories);
    }

    @GetMapping("/yearly-income-expenses")
    public ResponseEntity<YearlyIncomeExpensesDTO> yearlyIncomeExpenses() {
        YearlyIncomeExpensesDTO yearlyIncomeExpenses = homeService.getYearlyIncomeExpenses();

        return ResponseEntity.ok(yearlyIncomeExpenses);
    }

}
