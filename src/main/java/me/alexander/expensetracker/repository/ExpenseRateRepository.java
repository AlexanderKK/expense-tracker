package me.alexander.expensetracker.repository;

import me.alexander.expensetracker.model.entity.ExpenseRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseRateRepository extends JpaRepository<ExpenseRate, Long> {

}
