package me.alexander.expensetracker.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Table(name = "transactions")
public class Transaction extends BaseEntity {

    @Positive(message = "Expense amount should be positive")
    @NotNull(message = "Expense amount should not be empty")
    @Column(nullable = false)
    private Integer expense;

    @NotNull(message = "Transaction date should not be empty")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(nullable = false)
    private LocalDate date;

    @ManyToOne(optional = false)
    private Category category;

    public Integer getExpense() {
        return expense;
    }

    public Transaction setExpense(Integer expense) {
        this.expense = expense;

        return this;
    }

    public LocalDate getDate() {
        return date;
    }

    public Transaction setDate(LocalDate date) {
        this.date = date;

        return this;
    }

    public Category getCategory() {
        return category;
    }

    public Transaction setCategory(Category category) {
        this.category = category;

        return this;
    }

}
