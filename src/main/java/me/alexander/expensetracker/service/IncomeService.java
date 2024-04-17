package me.alexander.expensetracker.service;

import me.alexander.expensetracker.model.dto.income.AddIncomeDTO;

import java.util.List;

public interface IncomeService {

    void createIncome(AddIncomeDTO addIncomeDTO);

    double getTotalIncome();

    List<Double> getLastIncomes();

}
