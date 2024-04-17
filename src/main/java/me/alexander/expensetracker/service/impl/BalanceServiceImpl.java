package me.alexander.expensetracker.service.impl;

import me.alexander.expensetracker.model.dto.BalanceDTO;
import me.alexander.expensetracker.service.BalanceService;
import me.alexander.expensetracker.service.IncomeService;
import me.alexander.expensetracker.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;

@Service
public class BalanceServiceImpl implements BalanceService {

    private final TransactionService transactionService;
    private final IncomeService incomeService;

    @Autowired
    public BalanceServiceImpl(TransactionService transactionService,
                              IncomeService incomeService) {
        this.transactionService = transactionService;
        this.incomeService = incomeService;
    }

    @Override
    public BalanceDTO getTotalBalance() {
        double expenses = transactionService.getTotalExpenses();
        double income = incomeService.getTotalIncome();

        double balance = income - expenses;

        DecimalFormat balanceFormat = new DecimalFormat("#.##");
        double formattedBalance = Double.parseDouble(balanceFormat.format(balance));

        return new BalanceDTO(income, formattedBalance, expenses);
    }

}
