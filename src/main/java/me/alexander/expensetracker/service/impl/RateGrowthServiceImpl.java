package me.alexander.expensetracker.service.impl;

import me.alexander.expensetracker.service.IncomeService;
import me.alexander.expensetracker.service.RateGrowthService;
import me.alexander.expensetracker.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;

import static me.alexander.expensetracker.util.DateRange.*;

@Service
public class RateGrowthServiceImpl implements RateGrowthService {

    private final IncomeService incomeService;
    private final TransactionService transactionService;

    @Autowired
    public RateGrowthServiceImpl(IncomeService incomeService,
                                 TransactionService transactionService) {
        this.incomeService = incomeService;
        this.transactionService = transactionService;
    }

    @Override
    public double getMonthlyBalanceRate() {
        double lastMonthlyIncome = incomeService.getMonthlyIncomeByDate(FIRST_DAY_OF_LAST_MONTH, LAST_DAY_OF_LAST_MONTH);
        double lastMonthlyExpenses = transactionService.getMonthlyExpensesByDate(FIRST_DAY_OF_LAST_MONTH, LAST_DAY_OF_LAST_MONTH);

        double previousMonthlyIncome = incomeService.getMonthlyIncomeByDate(FIRST_DAY_OF_PREVIOUS_MONTH, LAST_DAY_OF_PREVIOUS_MONTH);
        double previousMonthlyExpenses = transactionService.getMonthlyExpensesByDate(FIRST_DAY_OF_PREVIOUS_MONTH, LAST_DAY_OF_PREVIOUS_MONTH);

        double lastMonthlyBalance = lastMonthlyIncome - lastMonthlyExpenses;
        double previousMonthlyBalance = previousMonthlyIncome - previousMonthlyExpenses;

        return calculateRateGrowth(lastMonthlyBalance, previousMonthlyBalance);
    }

    @Override
    public double getMonthlyIncomeRate() {
        double lastMonthlyIncome = incomeService.getMonthlyIncomeByDate(FIRST_DAY_OF_LAST_MONTH, LAST_DAY_OF_LAST_MONTH);
        double previousMonthlyIncome = incomeService.getMonthlyIncomeByDate(FIRST_DAY_OF_PREVIOUS_MONTH, LAST_DAY_OF_PREVIOUS_MONTH);

        return calculateRateGrowth(lastMonthlyIncome, previousMonthlyIncome);
    }

    @Override
    public double getMonthlyExpenseRate() {
        double lastMonthlyExpenses = transactionService.getMonthlyExpensesByDate(FIRST_DAY_OF_LAST_MONTH, LAST_DAY_OF_LAST_MONTH);
        double previousMonthlyExpenses = transactionService.getMonthlyExpensesByDate(FIRST_DAY_OF_PREVIOUS_MONTH, LAST_DAY_OF_PREVIOUS_MONTH);

        return calculateRateGrowth(lastMonthlyExpenses, previousMonthlyExpenses);
    }

    private static double calculateRateGrowth(double lastMonthlyAmount, double previousMonthlyAmount) {
        double rateGrowth;

        if(lastMonthlyAmount <= previousMonthlyAmount) {
            rateGrowth = 0.00;
        } else if(previousMonthlyAmount == 0) {
            rateGrowth = 100.00;
        } else {
            double monthlyRateGrowth = (lastMonthlyAmount - previousMonthlyAmount) / previousMonthlyAmount * 100;

            DecimalFormat rateGrowthFormat = new DecimalFormat("#.##");
            rateGrowth = Math.abs(
                    Double.parseDouble(
                            rateGrowthFormat.format(monthlyRateGrowth)));
        }

        return rateGrowth;
    }

}
