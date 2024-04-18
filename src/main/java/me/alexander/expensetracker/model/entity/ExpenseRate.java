package me.alexander.expensetracker.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "expense_rates")
public class ExpenseRate extends BaseEntity {

    private double expenseRateGrowth;
    private LocalDate firstDayOfLastMonth;
    private LocalDate lastDayOfLastMonth;
    private LocalDate firstDayOfPreviousMonth;
    private LocalDate lastDayOfPreviousMonth;

    public ExpenseRate() {}

    public ExpenseRate(double expenseRateGrowth,
                       LocalDate firstDayOfLastMonth,
                       LocalDate lastDayOfLastMonth,
                       LocalDate firstDayOfPreviousMonth,
                       LocalDate lastDayOfPreviousMonth) {
        this.expenseRateGrowth = expenseRateGrowth;
        this.firstDayOfLastMonth = firstDayOfLastMonth;
        this.lastDayOfLastMonth = lastDayOfLastMonth;
        this.firstDayOfPreviousMonth = firstDayOfPreviousMonth;
        this.lastDayOfPreviousMonth = lastDayOfPreviousMonth;
    }

    public double getExpenseRateGrowth() {
        return expenseRateGrowth;
    }

    public void setExpenseRateGrowth(double expenseRateGrowth) {
        this.expenseRateGrowth = expenseRateGrowth;
    }

    public LocalDate getFirstDayOfLastMonth() {
        return firstDayOfLastMonth;
    }

    public void setFirstDayOfLastMonth(LocalDate firstDayOfLastMonth) {
        this.firstDayOfLastMonth = firstDayOfLastMonth;
    }

    public LocalDate getLastDayOfLastMonth() {
        return lastDayOfLastMonth;
    }

    public void setLastDayOfLastMonth(LocalDate lastDayOfLastMonth) {
        this.lastDayOfLastMonth = lastDayOfLastMonth;
    }

    public LocalDate getFirstDayOfPreviousMonth() {
        return firstDayOfPreviousMonth;
    }

    public void setFirstDayOfPreviousMonth(LocalDate firstDayOfPreviousMonth) {
        this.firstDayOfPreviousMonth = firstDayOfPreviousMonth;
    }

    public LocalDate getLastDayOfPreviousMonth() {
        return lastDayOfPreviousMonth;
    }

    public void setLastDayOfPreviousMonth(LocalDate lastDayOfPreviousMonth) {
        this.lastDayOfPreviousMonth = lastDayOfPreviousMonth;
    }

}
