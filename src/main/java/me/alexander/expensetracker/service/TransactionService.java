package me.alexander.expensetracker.service;

import me.alexander.expensetracker.model.dto.transaction.AddTransactionDTO;

public interface TransactionService {

    void createTransaction(AddTransactionDTO addTransactionDTO);

}
