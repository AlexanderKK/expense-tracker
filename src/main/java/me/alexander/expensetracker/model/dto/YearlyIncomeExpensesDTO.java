package me.alexander.expensetracker.model.dto;

import java.util.List;

public class YearlyIncomeExpensesDTO {

    private List<Double> yearlyIncome;
    private List<Double> yearlyExpenses;

    public YearlyIncomeExpensesDTO() {}

    public YearlyIncomeExpensesDTO(List<Double> yearlyIncome, List<Double> yearlyExpenses) {
        this.yearlyIncome = yearlyIncome;
        this.yearlyExpenses = yearlyExpenses;
    }

    public List<Double> getYearlyIncome() {
        return yearlyIncome;
    }

    public void setYearlyIncome(List<Double> yearlyIncome) {
        this.yearlyIncome = yearlyIncome;
    }

    public List<Double> getYearlyExpenses() {
        return yearlyExpenses;
    }

    public void setYearlyExpenses(List<Double> yearlyExpenses) {
        this.yearlyExpenses = yearlyExpenses;
    }

}
