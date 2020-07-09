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
        System.out.println("Value: " + value);
        System.out.println("Values: " + Arrays.toString(values));
        boolean valid = (value == null) || Arrays.asList(values).contains(value);
        System.out.println("Valid: " + valid);
        if (!valid) {
            context.buildConstraintViolationWithTemplate(message)
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();
        }
        return valid;
    }

}
