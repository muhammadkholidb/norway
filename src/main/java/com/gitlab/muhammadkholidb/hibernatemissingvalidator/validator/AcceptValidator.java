package com.gitlab.muhammadkholidb.hibernatemissingvalidator.validator;

import java.util.Arrays;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.gitlab.muhammadkholidb.hibernatemissingvalidator.annotation.Accept;

public class AcceptValidator implements ConstraintValidator<Accept, Object> {

    private String[] values;
    private String message;

    @Override
    public void initialize(final Accept constraintAnnotation) {
        values = constraintAnnotation.value();
        message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(final Object value, final ConstraintValidatorContext context) {
        boolean valid = value == null || Arrays.asList(values).contains(value);
        if (!valid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
        }
        return valid;
    }

}
