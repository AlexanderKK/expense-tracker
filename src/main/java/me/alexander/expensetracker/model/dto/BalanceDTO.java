package me.alexander.expensetracker.model.dto;

import me.alexander.expensetracker.model.dto.income.RecentIncomesDTO;
import me.alexander.expensetracker.model.dto.transaction.RecentTransactionsDTO;

public class BalanceDTO {

    private RecentIncomesDTO incomeDTO;
    private double balance;
    private RecentTransactionsDTO transactionDTO;

    public BalanceDTO() {}

    public BalanceDTO(RecentIncomesDTO incomeDTO, double balance, RecentTransactionsDTO transactionDTO) {
        this.incomeDTO = incomeDTO;
        this.balance = balance;
        this.transactionDTO = transactionDTO;
    }

    public RecentIncomesDTO getIncomeDTO() {
        return incomeDTO;
    }

    public void setIncomeDTO(RecentIncomesDTO incomeDTO) {
        this.incomeDTO = incomeDTO;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public RecentTransactionsDTO getTransactionDTO() {
        return transactionDTO;
    }

    public void setTransactionDTO(RecentTransactionsDTO transactionDTO) {
        this.transactionDTO = transactionDTO;
    }

}
