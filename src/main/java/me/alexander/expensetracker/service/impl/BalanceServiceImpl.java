package me.alexander.expensetracker.service.impl;

import me.alexander.expensetracker.model.dto.BalanceDTO;
import me.alexander.expensetracker.model.dto.income.RecentIncomesDTO;
import me.alexander.expensetracker.model.dto.transaction.RecentTransactionsDTO;
import me.alexander.expensetracker.service.BalanceService;
import me.alexander.expensetracker.service.IncomeService;
import me.alexander.expensetracker.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.List;

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
        double totalIncome = incomeService.getTotalIncome();
        List<Double> lastIncomes = incomeService.getLastIncomes();
        RecentIncomesDTO recentIncomesDTO = new RecentIncomesDTO(totalIncome, lastIncomes);

        double totalExpenses = transactionService.getTotalExpenses();
        List<Double> lastExpenses = transactionService.getLastExpenses();
        RecentTransactionsDTO recentTransactionsDTO = new RecentTransactionsDTO(totalExpenses, lastExpenses);

        double balance = totalIncome - totalExpenses;

        DecimalFormat balanceFormat = new DecimalFormat("#.##");
        double formattedBalance = Double.parseDouble(balanceFormat.format(balance));

        return new BalanceDTO(recentIncomesDTO, formattedBalance, recentTransactionsDTO);
    }

}
