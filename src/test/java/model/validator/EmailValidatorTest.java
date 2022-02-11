package model.validator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class EmailValidatorTest {


    @ParameterizedTest
    @DisplayName("Email ValidityTest")
    @CsvSource(delimiter = '|',
            value = {
                "carlJohnsonj@gmail.com|true",
                "antony.a@spartaglobal.com|true",
                "???as/as.d|false",
                "antony@@gmail.com|false",
                "anto*y@gmail.com|false",
                "anto*y@gmailcom|false",
                "@gmail.com|false",
                "antony|false",
                "<antony>@gmail.com|false"
            }
    )

    void emailValidityTest(String email, boolean expected){
        Assertions.assertEquals(expected, new EmailValidator().validate(email));

    }
}
