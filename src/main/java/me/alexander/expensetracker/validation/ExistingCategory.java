package me.alexander.expensetracker.validation;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ExistingCategoryValidator.class)
public @interface ExistingCategory {

    String message() default "Category does not exist";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
