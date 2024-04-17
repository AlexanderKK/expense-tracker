package me.alexander.expensetracker.validation.date;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

public class DateRangeValidator implements ConstraintValidator<DateRange, String> {

    private LocalDate startDate;
    private LocalDate endDate;

    @Override
    public void initialize(DateRange constraintAnnotation) {
        this.startDate = LocalDate.parse(constraintAnnotation.startDate());
        this.endDate = LocalDate.parse(constraintAnnotation.endDate());
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        LocalDate date = LocalDate.parse(value);

        if(date.isBefore(this.startDate) || date.isAfter(this.endDate)) {
            String message = context
                    .getDefaultConstraintMessageTemplate()
                    .replace(
                            "{startDate} and {endDate}",
                            String.format("years %d and %d", startDate.getYear(), endDate.getYear())
                    );

            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(message).addConstraintViolation();

            return false;
        }

        return true;
    }

}
