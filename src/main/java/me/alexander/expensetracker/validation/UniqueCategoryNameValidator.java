package me.alexander.expensetracker.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import me.alexander.expensetracker.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class UniqueCategoryNameValidator implements ConstraintValidator<UniqueCategoryName, String> {

    private final CategoryRepository categoryRepository;

    @Autowired
    public UniqueCategoryNameValidator(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(value == null) {
            return true;
        }

        return categoryRepository.findByName(value).isEmpty();
    }

}
