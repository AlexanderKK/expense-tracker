package me.alexander.expensetracker.repository;

import me.alexander.expensetracker.model.entity.IncomeRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncomeRateRepository extends JpaRepository<IncomeRate, Long> {

}
