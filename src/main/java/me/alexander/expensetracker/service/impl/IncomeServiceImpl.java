package me.alexander.expensetracker.service.impl;

import me.alexander.expensetracker.model.dto.income.AddIncomeDTO;
import me.alexander.expensetracker.model.entity.Income;
import me.alexander.expensetracker.repository.IncomeRepository;
import me.alexander.expensetracker.service.IncomeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class IncomeServiceImpl implements IncomeService {

    private final ModelMapper mapper;
    private final IncomeRepository incomeRepository;

    @Autowired
    public IncomeServiceImpl(ModelMapper mapper,
                             IncomeRepository incomeRepository) {
        this.mapper = mapper;
        this.incomeRepository = incomeRepository;
    }

    @Override
    public void createIncome(AddIncomeDTO addIncomeDTO) {
        Income income = mapper.map(addIncomeDTO, Income.class);

        income.setDate(LocalDate.parse(addIncomeDTO.getDate()));

        incomeRepository.save(income);
    }

    @Override
    public double getTotalIncome() {
        return incomeRepository.findAll()
                .stream()
                .mapToInt(Income::getAmount)
                .sum();
    }

}
