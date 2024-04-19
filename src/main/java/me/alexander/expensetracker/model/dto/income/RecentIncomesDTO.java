package me.alexander.expensetracker.model.dto.income;

import java.util.List;

public class RecentIncomesDTO {

    private double totalIncome;
    private List<Double> lastIncomes;
    private double incomeRate;

    public RecentIncomesDTO(double totalIncome, List<Double> lastIncomes, double incomeRate) {
        this.totalIncome = totalIncome;
        this.lastIncomes = lastIncomes;
        this.incomeRate = incomeRate;
    }

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

    public double getIncomeRate() {
        return incomeRate;
    }

    public void setIncomeRate(double incomeRate) {
        this.incomeRate = incomeRate;
    }

}
