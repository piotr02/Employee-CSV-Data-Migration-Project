package model.validator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class GenderValidatorTest {

    @ParameterizedTest
    @DisplayName("Given a single Letter string that represents a gender or sex, expect a string that is equal to the letter" +
            "M, F or X to be valid")
    @CsvSource(delimiter = '|',
        value = {
            "M|true",
            "F|true",
            "X|true",
            "C|false",
            "Male|false",
            "Female|false"
        }
    )

    void genderValidityTest(String gender, boolean expected){
        Assertions.assertEquals(expected, new GenderValidator().validate(gender));

    }

}
