package me.alexander.expensetracker.web.rest;

import me.alexander.expensetracker.config.security.UserPrincipal;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminRestController {

    @GetMapping("/main")
    public ResponseEntity<String> admin(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        return ResponseEntity.ok(
                String.format("Administrator access: %s", userPrincipal.getEmail())
        );
    }

}
