package model.validator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class DateValidatorTest {


    @ParameterizedTest
    @DisplayName("Given Dates in the MM/dd/yyyy format, invalid dates should return false and valid dates should return true")
    @CsvSource(delimiter = '|',
            value = {
                "1/1/2000|true|New Years day exists every year",
                "-1/1/2000|false|There is no negative Month",
                "13/1/2000|false|There is no 13th Month",
                "2/29/2000|true|Is a leap year",
                "2/29/2001|false|Not a leap year",

            }
    )
    void dateValidityTest(String date, boolean expected, String message){
        Assertions.assertEquals(expected, new DateValidator("MM/dd/yyyy").validate(date), message);
    }
}
