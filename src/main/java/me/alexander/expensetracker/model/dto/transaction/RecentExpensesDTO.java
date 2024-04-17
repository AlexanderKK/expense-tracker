package me.alexander.expensetracker.model.dto.transaction;

import java.util.List;

public class RecentExpensesDTO {

    private double totalExpenses;
    private List<Double> lastExpenses;

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

}
