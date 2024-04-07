package me.alexander.expensetracker.service;

import me.alexander.expensetracker.model.dto.income.AddIncomeDTO;

public interface IncomeService {

    void createIncome(AddIncomeDTO addIncomeDTO);

    double getTotalIncome();

}
