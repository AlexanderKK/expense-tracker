package me.alexander.expensetracker.service.impl;

import me.alexander.expensetracker.model.dto.BalanceDTO;
import me.alexander.expensetracker.model.dto.income.RecentIncomesDTO;
import me.alexander.expensetracker.model.dto.transaction.RecentTransactionsDTO;
import me.alexander.expensetracker.service.BalanceService;
import me.alexander.expensetracker.service.IncomeService;
import me.alexander.expensetracker.service.RateGrowthService;
import me.alexander.expensetracker.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.List;

@Service
public class BalanceServiceImpl implements BalanceService {

    private final TransactionService transactionService;
    private final IncomeService incomeService;
    private final RateGrowthService rateGrowthService;

    @Autowired
    public BalanceServiceImpl(TransactionService transactionService,
                              IncomeService incomeService,
                              RateGrowthService rateGrowthService) {
        this.transactionService = transactionService;
        this.incomeService = incomeService;
        this.rateGrowthService = rateGrowthService;
    }

    @Override
    public BalanceDTO getBalance() {
        double totalIncome = incomeService.getTotalIncome();
        List<Double> lastIncomes = incomeService.getLastIncomes();
        double incomeRate = rateGrowthService.getMonthlyIncomeRate();

        RecentIncomesDTO recentIncomesDTO = new RecentIncomesDTO(totalIncome, lastIncomes, incomeRate);

        double totalExpenses = transactionService.getTotalExpenses();
        List<Double> lastExpenses = transactionService.getLastExpenses();
        double expenseRate = rateGrowthService.getMonthlyExpenseRate();

        RecentTransactionsDTO recentTransactionsDTO = new RecentTransactionsDTO(totalExpenses, lastExpenses, expenseRate);

        double balance = totalIncome - totalExpenses;

        DecimalFormat balanceFormat = new DecimalFormat("#.##");
        double formattedBalance = Double.parseDouble(balanceFormat.format(balance));

        return new BalanceDTO(recentIncomesDTO, formattedBalance, recentTransactionsDTO);
    }

}
