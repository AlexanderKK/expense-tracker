package me.alexander.expensetracker.validation.multipart;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import me.alexander.expensetracker.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

public class MultiPartFileValidator implements ConstraintValidator<MultiPartFile, MultipartFile> {

    private long size;
    private List<String> contentTypes;

    @Override
    public void initialize(MultiPartFile constraintAnnotation) {
        this.size = constraintAnnotation.size();
        this.contentTypes = Arrays.stream(constraintAnnotation.contentTypes()).toList();

        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(MultipartFile value, ConstraintValidatorContext context) {
        String errorMessage = generateErrorMessage(value);

        if (StringUtils.isNullOrEmpty(errorMessage)) {
            return true;
        }

        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(errorMessage).addConstraintViolation();

        return false;
    }

    private String generateErrorMessage(MultipartFile file) {
        if (file.getSize() > size) {
            return "File size exceeded. Max allowed size: " + size;
        }

        if (!contentTypes.contains(file.getContentType())) {
            return "Invalid file format. \nSupported files: " + String.join(", ", contentTypes);
        }

        return null;
    }

}
