package me.alexander.expensetracker.service;

import me.alexander.expensetracker.model.dto.BalanceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

        return new BalanceDTO(income, balance, expenses);
    }

}
