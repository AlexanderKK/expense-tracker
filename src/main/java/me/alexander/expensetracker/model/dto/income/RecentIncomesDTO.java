package me.alexander.expensetracker.model.dto.income;

import java.util.List;

public class RecentIncomesDTO {

    private double totalIncome;
    private List<Double> lastIncomes;

    public double getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(double totalIncome) {
        this.totalIncome = totalIncome;
    }

    public List<Double> getLastIncomes() {
        return lastIncomes;
    }

    public void setLastIncomes(List<Double> lastIncomes) {
        this.lastIncomes = lastIncomes;
    }

}
