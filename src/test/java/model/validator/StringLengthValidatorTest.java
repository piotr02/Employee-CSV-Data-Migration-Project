package model.validator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class StringLengthValidatorTest {

    @ParameterizedTest
    @CsvSource(
        delimiter = '|',
        value = {
            "HelloWorld|50|true",
            "HelloWorld|5|false",
            "HelloWorld|11|true",
            "HelloWorld|10|false"
        }
    )
    void testStringLengthValidity(String str, int maxLength, boolean expected){
        Assertions.assertEquals(expected, new StringLengthValidator(maxLength).validate(str));
    }
}
