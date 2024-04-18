package me.alexander.expensetracker.service.impl;

import me.alexander.expensetracker.model.dto.income.AddIncomeDTO;
import me.alexander.expensetracker.model.entity.Income;
import me.alexander.expensetracker.repository.IncomeRepository;
import me.alexander.expensetracker.service.IncomeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

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
        double totalIncome = incomeRepository.findAll()
                .stream()
                .mapToDouble(Income::getAmount)
                .sum();

        DecimalFormat incomeFormat = new DecimalFormat("#.##");

        return Double.parseDouble(incomeFormat.format(totalIncome));
    }

    @Override
    public List<Double> getLastIncomes() {
        Comparator<Income> creationDateComparator = Comparator
                .comparing(Income::getCreated)
                .reversed();

        return incomeRepository.findAll()
                .stream()
                .sorted(creationDateComparator)
                .map(Income::getAmount)
                .limit(3)
                .toList();
    }

    @Override
    public double getMonthlyIncomeByDate(LocalDate firstDayOfPreviousMonth, LocalDate lastDayOfPreviousMonth) {
        return incomeRepository.findAll()
                .stream()
                .filter(income -> !income.getDate().isBefore(firstDayOfPreviousMonth) && !income.getDate().isAfter(lastDayOfPreviousMonth))
                .mapToDouble(Income::getAmount)
                .sum();
    }

}
