package me.alexander.expensetracker.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
public class Transaction extends BaseEntity {

    @Positive(message = "Expense amount should be positive")
    @NotNull(message = "Expense amount should not be empty")
    @Column(nullable = false)
    private Double expense;

    @ManyToOne(optional = false)
    private Category category;

    @Size(max = 100, message = "Description length should not exceed 100")
    @Column
    private String description;

    @NotNull(message = "Transaction date should not be empty")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(nullable = false)
    private LocalDate date;

    @NotNull(message = "Creation date should not be empty")
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @Column(nullable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime created = LocalDateTime.now();

    public Double getExpense() {
        return expense;
    }

    public Transaction setExpense(Double expense) {
        this.expense = expense;

        return this;
    }

    public Category getCategory() {
        return category;
    }

    public Transaction setCategory(Category category) {
        this.category = category;

        return this;
    }

    public String getDescription() {
        return description;
    }

    public Transaction setDescription(String description) {
        this.description = description;

        return this;
    }

    public LocalDate getDate() {
        return date;
    }

    public Transaction setDate(LocalDate date) {
        this.date = date;

        return this;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

}
