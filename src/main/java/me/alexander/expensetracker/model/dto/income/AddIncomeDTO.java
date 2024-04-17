package me.alexander.expensetracker.model.dto.income;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import me.alexander.expensetracker.validation.date.DateRange;

public class AddIncomeDTO {

    @Positive(message = "Please enter a positive income amount")
    @NotNull(message = "Please enter an income amount")
    private Double amount;

    @NotBlank(message = "Please enter a source")
    private String source;

    @DateRange(startDate = "2000-01-01", endDate = "2050-12-31")
    @NotBlank(message = "Please pick a date")
    private String date;

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
