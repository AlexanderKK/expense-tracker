package me.alexander.expensetracker.service;

import me.alexander.expensetracker.model.dto.transaction.AddTransactionDTO;

import java.time.LocalDate;
import java.util.List;

public interface TransactionService {

    void createTransaction(AddTransactionDTO addTransactionDTO);

    double getMonthlyTotalExpenses();

    List<Double> getMonthlyLastExpenses();

    double getMonthlyExpensesByDate(LocalDate firstDayOfPreviousMonth, LocalDate lastDayOfPreviousMonth);

    List<Double> getYearlyExpenses();

}
