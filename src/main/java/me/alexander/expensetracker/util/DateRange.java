package me.alexander.expensetracker.util;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

public class DateRange {

    public static final LocalDate FIRST_DAY_OF_LAST_MONTH;
    public static final LocalDate LAST_DAY_OF_LAST_MONTH;
    public static final LocalDate FIRST_DAY_OF_PREVIOUS_MONTH;
    public static final LocalDate LAST_DAY_OF_PREVIOUS_MONTH;

    static {
        LocalDate currentDate = LocalDate.now();

        FIRST_DAY_OF_LAST_MONTH = currentDate.minusMonths(1).withDayOfMonth(1);
        LAST_DAY_OF_LAST_MONTH = currentDate.minusMonths(1).with(TemporalAdjusters.lastDayOfMonth());
        FIRST_DAY_OF_PREVIOUS_MONTH = currentDate.minusMonths(2).withDayOfMonth(1);
        LAST_DAY_OF_PREVIOUS_MONTH = currentDate.minusMonths(2).with(TemporalAdjusters.lastDayOfMonth());
    }

}
