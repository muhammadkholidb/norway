package com.gitlab.muhammadkholidb.hibernatemissingvalidator.validator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import com.gitlab.muhammadkholidb.hibernatemissingvalidator.annotation.FieldMatch;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import lombok.Data;

public class FieldMatchValidatorTest extends ValidatorTestBase {

    private FieldMatchValidator validator;

    @BeforeEach
    public void setUp() throws NoSuchFieldException, SecurityException {
        validator = new FieldMatchValidator();
        validator.initialize(TestObject.class.getAnnotation(FieldMatch.class));
    }

    @Test
    public void testIsValid_valuesAreTheSame_shoudlReturnTrue() {
        TestObject testObject = new TestObject();
        testObject.setName1("a");
        testObject.setName2("a");
        boolean result = validator.isValid(testObject, DEFAULT_CONTEXT);
        assertThat(result, is(true));
    }

    @Test
    public void testIsValid_valuesAreNull_shoudlReturnTrue() {
        boolean result = validator.isValid(new TestObject(), DEFAULT_CONTEXT);
        assertThat(result, is(true));
    }

    @Test
    public void testIsValid_valuesAreDifferent_shoudlReturnFalse() {
        TestObject testObject = new TestObject();
        testObject.setName1("a");
        testObject.setName2("b");
        boolean result = validator.isValid(testObject, DEFAULT_CONTEXT);
        assertThat(result, is(false));
    }

    @FieldMatch(first = "name1", second = "name2")
    @Data
    public static class TestObject {
        private String name1;
        private String name2;
    }

}
