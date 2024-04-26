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
import java.time.temporal.TemporalAdjusters;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

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
    public double getMonthlyTotalIncome() {
        double totalIncome = incomeRepository.findAll()
                .stream()
                .filter(IncomeServiceImpl::isForCurrentMonth)
                .mapToDouble(Income::getAmount)
                .sum();

        DecimalFormat incomeFormat = new DecimalFormat("#.##");

        return Double.parseDouble(incomeFormat.format(totalIncome));
    }

    @Override
    public List<Double> getMonthlyLastIncomes() {
        Comparator<Income> creationDateComparator = Comparator
                .comparing(Income::getCreated)
                .reversed();

        return incomeRepository.findAll()
                .stream()
                .filter(IncomeServiceImpl::isForCurrentMonth)
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

    @Override
    public List<Double> getYearlyIncome() {
        int currentYear = LocalDate.now().getYear();

        List<Double> yearlyIncome = incomeRepository.findAll()
                .stream()
                .filter(transaction -> transaction.getDate().getYear() == currentYear)
                .map(transaction -> {
                    IntStream intStream = IntStream.range(1, 13);

                    return intStream
                        .mapToObj(month -> {
                            LocalDate firstDayOfMonth = LocalDate.now().withMonth(month).withDayOfMonth(1);
                            LocalDate lastDayOfMonth = LocalDate.now().withMonth(month).with(TemporalAdjusters.lastDayOfMonth());

                            double monthlyIncome = this.getMonthlyIncomeByDate(firstDayOfMonth, lastDayOfMonth);
                            DecimalFormat incomeFormatter = new DecimalFormat("#.##");
                            double formattedMonthlyIncome = Double.parseDouble(incomeFormatter.format(monthlyIncome));

                            return formattedMonthlyIncome;
                        })
                        .toList();
                })
                .findFirst()
                .orElse(null);

        return yearlyIncome;
    }

    private static boolean isForCurrentMonth(Income income) {
        return income.getDate().getYear() == LocalDate.now().getYear() &&
                income.getDate().getMonthValue() == LocalDate.now().getMonthValue();
    }

}
