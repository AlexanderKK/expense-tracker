package me.alexander.expensetracker.service.impl;

import me.alexander.expensetracker.model.dto.YearlyIncomeExpensesDTO;
import me.alexander.expensetracker.service.HomeService;
import me.alexander.expensetracker.service.IncomeService;
import me.alexander.expensetracker.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HomeServiceImpl implements HomeService {

    private final IncomeService incomeService;
    private final TransactionService transactionService;

    @Autowired
    public HomeServiceImpl(IncomeService incomeService,
                           TransactionService transactionService) {
        this.incomeService = incomeService;
        this.transactionService = transactionService;
    }

    @Override
    public YearlyIncomeExpensesDTO getYearlyIncomeExpenses() {
        List<Double> yearlyIncome = incomeService.getYearlyIncome();
        List<Double> yearlyExpenses = transactionService.getYearlyExpenses();

        return new YearlyIncomeExpensesDTO(yearlyIncome, yearlyExpenses);
    }

}
