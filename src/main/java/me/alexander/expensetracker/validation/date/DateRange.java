package me.alexander.expensetracker.validation.date;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DateRangeValidator.class)
public @interface DateRange {

    String startDate();

    String endDate() default "";

    String message() default "Date should be between {startDate} and {endDate}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
