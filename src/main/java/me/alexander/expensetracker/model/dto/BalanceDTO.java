package me.alexander.expensetracker.model.dto;

import me.alexander.expensetracker.model.dto.income.RecentIncomesDTO;
import me.alexander.expensetracker.model.dto.transaction.RecentTransactionsDTO;

public class BalanceDTO {

    private RecentIncomesDTO incomesDTO;
    private double balance;
    private RecentTransactionsDTO transactionsDTO;

    public BalanceDTO() {}

    public BalanceDTO(RecentIncomesDTO incomesDTO, double balance, RecentTransactionsDTO transactionsDTO) {
        this.incomesDTO = incomesDTO;
        this.balance = balance;
        this.transactionsDTO = transactionsDTO;
    }

    public RecentIncomesDTO getIncomesDTO() {
        return incomesDTO;
    }

    public void setIncomesDTO(RecentIncomesDTO incomesDTO) {
        this.incomesDTO = incomesDTO;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public RecentTransactionsDTO getTransactionsDTO() {
        return transactionsDTO;
    }

    public void setTransactionsDTO(RecentTransactionsDTO transactionsDTO) {
        this.transactionsDTO = transactionsDTO;
    }

}
