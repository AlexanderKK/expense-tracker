package me.alexander.expensetracker.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "incomes")
public class Income extends BaseEntity {

    @Positive(message = "Income amount should be positive")
    @NotNull(message = "Income amount should not be empty")
    @Column(nullable = false)
    private Double amount;

    @NotBlank(message = "Income source should not be empty")
    @Column(nullable = false)
    private String source;

    @NotNull(message = "Income date should not be empty")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(nullable = false)
    private LocalDate date;

    @NotNull(message = "Creation date should not be empty")
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @Column(nullable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime created = LocalDateTime.now();

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

}
