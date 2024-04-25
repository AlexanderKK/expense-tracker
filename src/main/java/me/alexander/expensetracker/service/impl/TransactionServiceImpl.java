package me.alexander.expensetracker.service.impl;

import jakarta.persistence.EntityNotFoundException;
import me.alexander.expensetracker.model.dto.transaction.AddTransactionDTO;
import me.alexander.expensetracker.model.entity.Transaction;
import me.alexander.expensetracker.repository.CategoryRepository;
import me.alexander.expensetracker.repository.TransactionRepository;
import me.alexander.expensetracker.service.TransactionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

import static me.alexander.expensetracker.constants.Messages.ENTITY_NOT_FOUND;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final ModelMapper mapper;
    private final CategoryRepository categoryRepository;

    @Autowired
    public TransactionServiceImpl(ModelMapper mapper,
                                  TransactionRepository transactionRepository,
                                  CategoryRepository categoryRepository) {
        this.transactionRepository = transactionRepository;
        this.mapper = mapper;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void createTransaction(AddTransactionDTO addTransactionDTO) {
        Transaction transaction = mapper.map(addTransactionDTO, Transaction.class);
        transaction.setCategory(
                categoryRepository.findById(addTransactionDTO.getCategory())
                        .orElseThrow(() -> new EntityNotFoundException(String.format(ENTITY_NOT_FOUND, "Category")))
        ).setDate(LocalDate.parse(addTransactionDTO.getDate()));

        transactionRepository.save(transaction);
    }

    @Override
    public double getTotalExpenses() {
        double totalExpenses = transactionRepository.findAll()
                .stream()
                .mapToDouble(Transaction::getExpense)
                .sum();

        DecimalFormat expensesFormat = new DecimalFormat("#.##");

        return Double.parseDouble(expensesFormat.format(totalExpenses));
    }

    @Override
    public List<Double> getLastExpenses() {
        Comparator<Transaction> creationDateComparator = Comparator
                .comparing(Transaction::getCreated)
                .reversed();

        return transactionRepository.findAll()
                .stream()
                .sorted(creationDateComparator)
                .map(Transaction::getExpense)
                .limit(3)
                .toList();
    }

    @Override
    public double getMonthlyExpensesByDate(LocalDate firstDayOfMonth, LocalDate lastDayOfMonth) {
        return transactionRepository.findAll()
                .stream()
                .filter(transaction -> !transaction.getDate().isBefore(firstDayOfMonth) && !transaction.getDate().isAfter(lastDayOfMonth))
                .mapToDouble(Transaction::getExpense)
                .sum();
    }

    @Override
    public List<Double> getYearlyExpenses() {
        int currentYear = LocalDate.now().getYear();

        List<Double> yearlyExpenses = transactionRepository.findAll()
                .stream()
                .filter(transaction -> transaction.getDate().getYear() == currentYear)
                .map(transaction -> {
                    IntStream intStream = IntStream.range(1, 13);

                    return intStream
                        .mapToObj(month -> {
                            LocalDate firstDayOfMonth = LocalDate.now().withMonth(month).withDayOfMonth(1);
                            LocalDate lastDayOfMonth = LocalDate.now().withMonth(month).with(TemporalAdjusters.lastDayOfMonth());

                            double monthlyExpenses = this.getMonthlyExpensesByDate(firstDayOfMonth, lastDayOfMonth);
                            DecimalFormat expensesFormatter = new DecimalFormat("#.##");
                            double formattedMonthlyExpenses = Double.parseDouble(expensesFormatter.format(monthlyExpenses));

                            return formattedMonthlyExpenses;
                        })
                        .toList();
                })
                .findFirst()
                .orElse(null);

        return yearlyExpenses;
    }

}
