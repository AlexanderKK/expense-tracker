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
import java.util.Comparator;
import java.util.List;

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
        Comparator<Transaction> dateComparator = Comparator
                .comparing(Transaction::getDate)
                .reversed();

        return transactionRepository.findAll()
                .stream()
                .sorted(dateComparator)
                .map(Transaction::getExpense)
                .limit(3)
                .toList();
    }

}
