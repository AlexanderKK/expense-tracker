package me.alexander.expensetracker.validation.category;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import me.alexander.expensetracker.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ExistingCategoryValidator implements ConstraintValidator<ExistingCategory, Long> {

    private final CategoryRepository categoryRepository;

    @Autowired
    public ExistingCategoryValidator(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        if(value == null) {
            return true;
        }

        return this.categoryRepository.findById(value).isPresent();
    }

}
