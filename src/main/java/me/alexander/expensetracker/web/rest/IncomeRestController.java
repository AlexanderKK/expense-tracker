package me.alexander.expensetracker.web.rest;

import jakarta.validation.Valid;
import me.alexander.expensetracker.model.dto.income.AddIncomeDTO;
import me.alexander.expensetracker.service.IncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/incomes")
public class IncomeRestController {

    private final IncomeService incomeService;

    @Autowired
    public IncomeRestController(IncomeService incomeService) {
        this.incomeService = incomeService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> createIncome(@Valid @RequestBody AddIncomeDTO addIncomeDTO) {
        incomeService.createIncome(addIncomeDTO);

        return ResponseEntity.ok().build();
    }

}
