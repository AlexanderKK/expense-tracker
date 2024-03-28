package me.alexander.expensetracker.service.impl;

import me.alexander.expensetracker.model.dto.transaction.AddTransactionDTO;
import me.alexander.expensetracker.model.entity.Transaction;
import me.alexander.expensetracker.repository.TransactionRepository;
import me.alexander.expensetracker.service.TransactionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final ModelMapper mapper;

    @Autowired
    public TransactionServiceImpl(TransactionRepository transactionRepository,
                                  ModelMapper mapper) {
        this.transactionRepository = transactionRepository;
        this.mapper = mapper;
    }

    @Override
    public void createTransaction(AddTransactionDTO addTransactionDTO) {
        Transaction transaction = mapper.map(addTransactionDTO, Transaction.class);

        transactionRepository.save(transaction);
    }

}
