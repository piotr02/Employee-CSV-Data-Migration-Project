package model.validator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class StringEndsWithValidatorTest {


    @ParameterizedTest
    @CsvSource(
        delimiter = '|',
        value = {
            "Dr.|.|true",
            "Dr|.|false"
        }
    )
    void testStringEndsWithValidity(String str, String endsWith, boolean expected){
        Assertions.assertEquals(expected, new StringEndsWithValidator(endsWith).validate(str));

    }


}
