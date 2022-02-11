package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.text.ParseException;

public class EmployeeCsvDataValidatorNewTest {


    @Test
    @DisplayName("Given CSV 3 records with 2 with missing values expect 2 records with missing field, 2 incorrect records," +
            "0 duplicated records 1 unique valid record")
    void testMissingValues(){

        String[][] data = CSVReader.readCsvFile("TestMissingValuesData.csv");

        EmployeeCsvDataValidatorNew validator = new EmployeeCsvDataValidatorNew(data);
        Assertions.assertEquals(2, validator.getRecordsWithMissingFields().size());
    }

    @Test
    @DisplayName("Given A CSV file where ther are 11 records and 10 records have 1 invalid field, expect the employee validator" +
            "to return 1 for valid field and return 10 invalid field")
    void testIncorrectRecords(){



    }

    @Test
    @DisplayName("Given a CSV file where there are 8 values and there are two duplicated for employeeId, expect the employee" +
            "validator to return 2 duplicate rows and 6 correct rows, 0 rows with incorrect fielsd and 0 rows with missing fields")
    void testForDuplicateRecords(){
        String[][] data = CSVReader.readCsvFile("TestDuplicatedData.csv");
        EmployeeCsvDataValidatorNew validator = new EmployeeCsvDataValidatorNew(data);
        Assertions.assertEquals(0, validator.getRecordsWithMissingFields().size());
        Assertions.assertEquals(0, validator.getRecordsWithIncorrectFields().size());
        Assertions.assertEquals(2, validator.getRecordsWithDuplicatedId().size());
        Assertions.assertEquals(6, validator.getUniqueCleanRecords().size());


    }
}
