package me.alexander.expensetracker.model.dto.transaction;

import java.util.List;

public class RecentTransactionsDTO {

    private double totalExpenses;
    private List<Double> lastExpenses;
    private double expenseRate;

    public RecentTransactionsDTO(double totalExpenses, List<Double> lastExpenses, double expenseRate) {
        this.totalExpenses = totalExpenses;
        this.lastExpenses = lastExpenses;
        this.expenseRate = expenseRate;
    }

    public double getTotalExpenses() {
        return totalExpenses;
    }

    public void setTotalExpenses(double totalExpenses) {
        this.totalExpenses = totalExpenses;
    }

    public List<Double> getLastExpenses() {
        return lastExpenses;
    }

    public void setLastExpenses(List<Double> lastExpenses) {
        this.lastExpenses = lastExpenses;
    }

    public double getExpenseRate() {
        return expenseRate;
    }

    public void setExpenseRate(double expenseRate) {
        this.expenseRate = expenseRate;
    }

}
