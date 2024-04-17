package me.alexander.expensetracker.service;

import me.alexander.expensetracker.model.dto.transaction.AddTransactionDTO;

import java.util.List;

public interface TransactionService {

    void createTransaction(AddTransactionDTO addTransactionDTO);

    double getTotalExpenses();

    List<Double> getLastExpenses();

}
