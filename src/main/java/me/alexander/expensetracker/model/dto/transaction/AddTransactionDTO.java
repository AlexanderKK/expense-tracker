package me.alexander.expensetracker.model.dto.transaction;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import me.alexander.expensetracker.validation.ExistingCategory;

public class AddTransactionDTO {

    @Positive(message = "Please enter a positive expense amount")
    @NotNull(message = "Please enter an expense amount")
    private Integer expense;

    @NotNull(message = "Please choose a category")
    @ExistingCategory
    private Long category;

    public Integer getExpense() {
        return expense;
    }

    public void setExpense(Integer expense) {
        this.expense = expense;
    }

    public Long getCategory() {
        return category;
    }

    public void setCategory(Long category) {
        this.category = category;
    }

}
