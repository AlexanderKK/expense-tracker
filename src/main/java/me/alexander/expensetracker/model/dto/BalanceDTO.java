package me.alexander.expensetracker.model.dto;

import me.alexander.expensetracker.model.dto.income.RecentIncomesDTO;
import me.alexander.expensetracker.model.dto.transaction.RecentExpensesDTO;

public class BalanceDTO {

    private RecentIncomesDTO income;
    private double balance;
    private RecentExpensesDTO expenses;

    public BalanceDTO() {}

    public BalanceDTO(RecentIncomesDTO income, double balance, RecentExpensesDTO expenses) {
        this.income = income;
        this.balance = balance;
        this.expenses = expenses;
    }

    public RecentIncomesDTO getIncome() {
        return income;
    }

    public void setIncome(RecentIncomesDTO income) {
        this.income = income;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public RecentExpensesDTO getExpenses() {
        return expenses;
    }

    public void setExpenses(RecentExpensesDTO expenses) {
        this.expenses = expenses;
    }

}
