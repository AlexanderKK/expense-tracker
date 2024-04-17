package me.alexander.expensetracker.model.dto.transaction;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import me.alexander.expensetracker.validation.category.ExistingCategory;
import me.alexander.expensetracker.validation.date.DateRange;

public class AddTransactionDTO {

    @Positive(message = "Please enter a positive expense amount")
    @NotNull(message = "Please enter an expense amount")
    private Integer expense;

    @NotNull(message = "Please choose a category")
    @ExistingCategory
    private Long category;

    @Size(message = "Description length should not exceed 100")
    private String description;

    @DateRange(startDate = "2000-01-01", endDate = "2050-12-31")
    @NotBlank(message = "Please pick a date")
    private String date;

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
