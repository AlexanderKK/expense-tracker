package me.alexander.expensetracker.model.dto.transaction;

import jakarta.validation.constraints.*;
import me.alexander.expensetracker.validation.category.ExistingCategory;
import me.alexander.expensetracker.validation.date.DateRange;

public class AddTransactionDTO {

    @Positive(message = "Please enter a positive expense amount")
    @NotNull(message = "Please enter an expense amount")
    private Double expense;

    @NotNull(message = "Please choose a category")
    @ExistingCategory
    private Long category;

    @Size(max = 100, message = "Description length should not exceed 100")
    private String description;

    @DateRange(startDate = "2000-01-01")
    @NotBlank(message = "Please pick a date")
    private String date;

    public Double getExpense() {
        return expense;
    }

    public void setExpense(Double expense) {
        this.expense = expense;
    }

    public Long getCategory() {
        return category;
    }

    public void setCategory(Long category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
