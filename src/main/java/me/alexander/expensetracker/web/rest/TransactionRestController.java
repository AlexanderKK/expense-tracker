package me.alexander.expensetracker.web.rest;

import jakarta.validation.Valid;
import me.alexander.expensetracker.model.dto.transaction.AddTransactionDTO;
import me.alexander.expensetracker.model.dto.transaction.TransactionDTO;
import me.alexander.expensetracker.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionRestController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionRestController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping
    public ResponseEntity<List<TransactionDTO>> transactions() {
        List<TransactionDTO> transactions = transactionService.getAllTransactions();

        return ResponseEntity.ok(transactions);
    }

    @PostMapping("/add")
    public ResponseEntity<?> createTransaction(@Valid @RequestBody AddTransactionDTO addTransactionDTO) {
        transactionService.createTransaction(addTransactionDTO);

        return ResponseEntity.ok().build();
    }

}
