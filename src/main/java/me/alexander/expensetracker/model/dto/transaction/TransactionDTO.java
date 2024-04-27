package me.alexander.expensetracker.model.dto.transaction;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class TransactionDTO {

    @Positive(message = "Expense amount should be positive")
    @NotNull(message = "Expense amount should not be empty")
    private Double expense;

    @NotBlank(message = "Category should not be empty")
    private String category;

    @Size(max = 100, message = "Description length should not exceed 100")
    private String description;

    @NotNull(message = "Transaction date should not be empty")
    private String date;

    public Double getExpense() {
        return expense;
    }

    public void setExpense(Double expense) {
        this.expense = expense;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
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
