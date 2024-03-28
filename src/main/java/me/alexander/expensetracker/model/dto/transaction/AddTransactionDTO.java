package me.alexander.expensetracker.model.dto.transaction;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import me.alexander.expensetracker.model.dto.category.CategoryDTO;

public class AddTransactionDTO {

    @Positive(message = "Expense should be positive")
    @NotNull(message = "Please enter an expense amount")
    private Integer expense;

    @NotNull(message = "Please enter a category")
    private CategoryDTO categoryDTO;

    public Integer getExpense() {
        return expense;
    }

    public void setExpense(Integer expense) {
        this.expense = expense;
    }

    public CategoryDTO getCategoryDTO() {
        return categoryDTO;
    }

    public void setCategoryDTO(CategoryDTO categoryDTO) {
        this.categoryDTO = categoryDTO;
    }

}
