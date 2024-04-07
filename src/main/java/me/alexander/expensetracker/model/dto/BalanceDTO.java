package me.alexander.expensetracker.model.dto;

import com.fasterxml.jackson.databind.ser.Serializers;

public class BalanceDTO {

    private double income;
    private double balance;
    private double expenses;

    public BalanceDTO(double income, double balance, double expenses) {
        this.income = income;
        this.balance = balance;
        this.expenses = expenses;
    }

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getExpenses() {
        return expenses;
    }

    public void setExpenses(double expenses) {
        this.expenses = expenses;
    }

}
