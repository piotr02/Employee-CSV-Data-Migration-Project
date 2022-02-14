package model.validator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class IntegerValidatorTest {


    @ParameterizedTest
    @CsvSource(
            delimiter = '|',
            value =
            {"32|true", "s2|false", "4|true", "1|true", "a|false", "AB|false", "2|true", "3|true"}
    )
    void testIntegerValidity(String number, boolean expected){
        Assertions.assertEquals(expected, new IntegerValidator().validate(number));

    }
}
