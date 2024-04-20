package me.alexander.expensetracker.service;

public interface RateGrowthService {

    double getMonthlyBalanceRate();

    double getMonthlyIncomeRate();

    double getMonthlyExpenseRate();

}
