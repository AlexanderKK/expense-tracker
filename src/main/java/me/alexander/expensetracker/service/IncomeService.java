package me.alexander.expensetracker.service;

import me.alexander.expensetracker.model.dto.income.AddIncomeDTO;

import java.time.LocalDate;
import java.util.List;

public interface IncomeService {

    void createIncome(AddIncomeDTO addIncomeDTO);

    double getTotalIncome();

    List<Double> getLastIncomes();

    double getMonthlyIncomeByDate(LocalDate firstDayOfPreviousMonth, LocalDate lastDayOfPreviousMonth);

}
