package com.gitlab.muhammadkholidb.hibernatemissingvalidator.validator;

import java.util.Objects;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.gitlab.muhammadkholidb.hibernatemissingvalidator.annotation.FieldMatch;

import org.apache.commons.beanutils.BeanUtils;

public class FieldMatchValidator implements ConstraintValidator<FieldMatch, Object> {

    private String firstFieldName;
    private String secondFieldName;
    private String message;

    @Override
    public void initialize(final FieldMatch constraintAnnotation) {
        firstFieldName = constraintAnnotation.first();
        secondFieldName = constraintAnnotation.second();
        message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(final Object value, final ConstraintValidatorContext context) {
        boolean valid = true;
        try {
            final Object firstObj = BeanUtils.getProperty(value, firstFieldName);
            final Object secondObj = BeanUtils.getProperty(value, secondFieldName);
            valid = Objects.equals(secondObj, firstObj);
        } catch (Exception ex) {
            // ignore
        }
        if (!valid) {
            context.buildConstraintViolationWithTemplate(message).addPropertyNode(secondFieldName)
                    .addConstraintViolation().disableDefaultConstraintViolation();
        }
        return valid;
    }

}
