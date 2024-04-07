package me.alexander.expensetracker.web.rest;

import me.alexander.expensetracker.model.dto.BalanceDTO;
import me.alexander.expensetracker.service.BalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/balance")
public class BalanceRestController {

    private final BalanceService balanceService;

    @Autowired
    public BalanceRestController(BalanceService balanceService) {
        this.balanceService = balanceService;
    }

    @GetMapping
    public ResponseEntity<BalanceDTO> balance() {
        BalanceDTO balanceDTO = balanceService.getTotalBalance();

        return ResponseEntity.ok(balanceDTO);
    }

}
