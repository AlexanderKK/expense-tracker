package me.alexander.expensetracker.service.schedulers;

import me.alexander.expensetracker.service.RateGrowthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ExpenseRateScheduler {

    private final RateGrowthService rateGrowthService;

    @Autowired
    public ExpenseRateScheduler(RateGrowthService rateGrowthService) {
        this.rateGrowthService = rateGrowthService;
    }

//    @Scheduled(cron = "0 0 0 1 * *")
    @Scheduled(cron = "0/15 * * * * *")
    public void calculateExpenseRateGrowth() {
        rateGrowthService.saveMonthlyIncomeRate();
        rateGrowthService.saveMonthlyExpenseRate();
    }

}
