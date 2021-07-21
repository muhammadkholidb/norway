package com.gitlab.muhammadkholidb.hibernatemissingvalidator.validator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import com.gitlab.muhammadkholidb.hibernatemissingvalidator.annotation.Accept;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import lombok.Data;

public class AcceptValidatorTest extends ValidatorTestBase {

    private AcceptValidator validator;

    @BeforeEach
    public void setUp() throws NoSuchFieldException, SecurityException {
        validator = new AcceptValidator();
        validator.initialize(TestObject.class.getDeclaredField("name").getAnnotation(Accept.class));
    }

    @Test
    public void testIsValid_valueIsFoo_shoudlReturnTrue() {
        boolean result = validator.isValid("foo", DEFAULT_CONTEXT);
        assertThat(result, is(true));
    }

    @Test
    public void testIsValid_valueIsNull_shoudlReturnTrue() {
        boolean result = validator.isValid(null, DEFAULT_CONTEXT);
        assertThat(result, is(true));
    }

    @Test
    public void testIsValid_valueIsAny_shoudlReturnFalse() {
        boolean result = validator.isValid("any", DEFAULT_CONTEXT);
        assertThat(result, is(false));
    }

    @Data
    private class TestObject {
        @Accept({ "foo", "bar" })
        private String name;
    }

}
