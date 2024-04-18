package me.alexander.expensetracker.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "income_rates")
public class IncomeRate extends BaseEntity {

    private double incomeRateGrowth;
    private LocalDate firstDayOfLastMonth;
    private LocalDate lastDayOfLastMonth;
    private LocalDate firstDayOfPreviousMonth;
    private LocalDate lastDayOfPreviousMonth;

    public IncomeRate() {}

    public IncomeRate(double incomeRateGrowth,
                      LocalDate firstDayOfLastMonth,
                      LocalDate lastDayOfLastMonth,
                      LocalDate firstDayOfPreviousMonth,
                      LocalDate lastDayOfPreviousMonth) {
        this.incomeRateGrowth = incomeRateGrowth;
        this.firstDayOfLastMonth = firstDayOfLastMonth;
        this.lastDayOfLastMonth = lastDayOfLastMonth;
        this.firstDayOfPreviousMonth = firstDayOfPreviousMonth;
        this.lastDayOfPreviousMonth = lastDayOfPreviousMonth;
    }

    public double getIncomeRateGrowth() {
        return incomeRateGrowth;
    }

    public void setIncomeRateGrowth(double incomeRateGrowth) {
        this.incomeRateGrowth = incomeRateGrowth;
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
