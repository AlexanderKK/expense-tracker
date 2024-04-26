package me.alexander.expensetracker.validation.date;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateRangeValidator implements ConstraintValidator<DateRange, String> {

    private LocalDate startDate;
    private LocalDate endDate;

    @Override
    public void initialize(DateRange constraintAnnotation) {
        this.startDate = LocalDate.parse(constraintAnnotation.startDate());
        this.endDate = getEndDate(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        LocalDate date = LocalDate.parse(value);

        if(date.isBefore(this.startDate) || date.isAfter(this.endDate)) {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

            String startDate = this.startDate.format(dateFormatter);
            String endDate = this.endDate.format(dateFormatter);

            String message = context
                    .getDefaultConstraintMessageTemplate()
                    .replace(
                            "{startDate} and {endDate}",
                            String.format("%s and %s", startDate, endDate)
                    );

            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(message).addConstraintViolation();

            return false;
        }

        return true;
    }

    private static LocalDate getEndDate(DateRange constraintAnnotation) {
        String endDateString = constraintAnnotation.endDate();

        if(endDateString.isEmpty()) {
            return LocalDate.now();
        } else {
            return LocalDate.parse(endDateString);
        }
    }

}
