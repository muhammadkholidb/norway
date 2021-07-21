package com.gitlab.muhammadkholidb.norway.validator;

import javax.validation.ConstraintValidatorContext;

import org.hibernate.validator.internal.engine.DefaultClockProvider;
import org.hibernate.validator.internal.engine.constraintvalidation.ConstraintValidatorContextImpl;
import org.hibernate.validator.internal.engine.path.PathImpl;

public class ValidatorTestBase {

    protected static final ConstraintValidatorContext DEFAULT_CONTEXT = new ConstraintValidatorContextImpl(
            DefaultClockProvider.INSTANCE, PathImpl.createRootPath(), null, null);

}
