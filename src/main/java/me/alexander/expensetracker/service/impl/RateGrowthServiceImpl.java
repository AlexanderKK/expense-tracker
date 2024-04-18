package me.alexander.expensetracker.service.impl;

import me.alexander.expensetracker.model.entity.ExpenseRate;
import me.alexander.expensetracker.model.entity.IncomeRate;
import me.alexander.expensetracker.repository.ExpenseRateRepository;
import me.alexander.expensetracker.repository.IncomeRateRepository;
import me.alexander.expensetracker.service.RateGrowthService;
import me.alexander.expensetracker.service.IncomeService;
import me.alexander.expensetracker.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

@Service
public class RateGrowthServiceImpl implements RateGrowthService {

    private final IncomeService incomeService;
    private final TransactionService transactionService;
    private final IncomeRateRepository incomeRateRepository;
    private final ExpenseRateRepository expenseRateRepository;

    @Autowired
    public RateGrowthServiceImpl(IncomeService incomeService,
                                 TransactionService transactionService,
                                 IncomeRateRepository incomeRateRepository,
                                 ExpenseRateRepository expenseRateRepository) {
        this.incomeService = incomeService;
        this.transactionService = transactionService;
        this.incomeRateRepository = incomeRateRepository;
        this.expenseRateRepository = expenseRateRepository;
    }

    @Override
    public void saveMonthlyIncomeRate() {
        LocalDate currentDate = LocalDate.now();

        LocalDate firstDayOfLastMonth = currentDate.minusMonths(1).withDayOfMonth(1);
        LocalDate lastDayOfLastMonth = currentDate.minusMonths(1).with(TemporalAdjusters.lastDayOfMonth());

        double currentMonthlyIncome = incomeService.getMonthlyIncomeByDate(firstDayOfLastMonth, lastDayOfLastMonth);

        LocalDate firstDayOfPreviousMonth = currentDate.minusMonths(2).withDayOfMonth(1);
        LocalDate lastDayOfPreviousMonth = currentDate.minusMonths(2).with(TemporalAdjusters.lastDayOfMonth());

        double previousMonthlyIncome = incomeService.getMonthlyIncomeByDate(firstDayOfPreviousMonth, lastDayOfPreviousMonth);

        // Calculate income rate
        double incomeRate = getRateGrowth(currentMonthlyIncome, previousMonthlyIncome);

        IncomeRate incomeRateEntity = new IncomeRate(
                incomeRate, firstDayOfLastMonth, lastDayOfLastMonth, firstDayOfPreviousMonth, lastDayOfPreviousMonth
        );

        incomeRateRepository.save(incomeRateEntity);
    }

    @Override
    public void saveMonthlyExpenseRate() {
        LocalDate currentDate = LocalDate.now();

        LocalDate firstDayOfLastMonth = currentDate.minusMonths(1).withDayOfMonth(1);
        LocalDate lastDayOfLastMonth = currentDate.minusMonths(1).with(TemporalAdjusters.lastDayOfMonth());

        double currentMonthlyExpenses = transactionService.getMonthlyExpensesByDate(firstDayOfLastMonth, lastDayOfLastMonth);

        LocalDate firstDayOfPreviousMonth = currentDate.minusMonths(2).withDayOfMonth(1);
        LocalDate lastDayOfPreviousMonth = currentDate.minusMonths(2).with(TemporalAdjusters.lastDayOfMonth());

        double previousMonthlyExpenses = transactionService.getMonthlyExpensesByDate(firstDayOfPreviousMonth, lastDayOfPreviousMonth);

        // Calculate expense rate
        double expenseRate = getRateGrowth(currentMonthlyExpenses, previousMonthlyExpenses);

        ExpenseRate expenseRateEntity = new ExpenseRate(
                expenseRate, firstDayOfLastMonth, lastDayOfLastMonth, firstDayOfPreviousMonth, lastDayOfPreviousMonth
        );

        expenseRateRepository.save(expenseRateEntity);
    }

    private static double getRateGrowth(double currentMonthlyAmount, double previousMonthlyAmount) {
        double rateGrowth;

        if(currentMonthlyAmount <= previousMonthlyAmount) {
            rateGrowth = 0;
        } else if(previousMonthlyAmount == 0) {
            rateGrowth = 100;
        } else {
            double monthlyRateGrowth = (currentMonthlyAmount - previousMonthlyAmount) / previousMonthlyAmount * 100;

            DecimalFormat rateGrowthFormat = new DecimalFormat("#.##");
            rateGrowth = Double.parseDouble(
                    rateGrowthFormat.format(monthlyRateGrowth)
            );
        }

        return rateGrowth;
    }

}
