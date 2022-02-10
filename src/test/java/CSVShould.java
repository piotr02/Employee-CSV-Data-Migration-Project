
import model.CSVReader;
import model.EmployeeCsvDataValidator;
import model.EmployeeCsvDataValidatorNew;
import org.junit.jupiter.api.*;
import view.CSVView;

import java.util.ArrayList;
import java.util.Arrays;

import static model.CSVReader.*;
import static org.junit.jupiter.api.Assertions.*;


public class CSVShould {

    @Test
    @DisplayName("Upon running the CSV Reader, an input should be returned and the file should successfully be read.")
    public void returnFile() {

        System.out.println(Arrays.deepToString(
                readCsvFile("EmployeeRecords.csv")));
    }


    @Test
    @DisplayName("When the program is run, the collection should be populated with an object.")
    public void recordGetsAddedToCollection() {
        CSVReader testReader = new CSVReader();
        readCsvFile("EmployeeRecords.csv");
        String stringTest = CSVReader.readCsvFile("authors.csv").toString();
        assertNotNull(stringTest);
    }

    @Test
    @DisplayName("When the program is run, corrupt data should be read and populate a seperate collection.")
    public void corruptRecordGetsAddedToDifferentCollection() {
        EmployeeCsvDataValidator dataValidator = new EmployeeCsvDataValidator();
        dataValidator.setData(CSVReader.readCsvFile("EmployeeRecords.csv"));
        dataValidator.splitData();
        ArrayList<String[]> corruptedData =  dataValidator.getCorrupted();
        assertNotNull(dataValidator.getCorrupted());
    }

    @Test
    @DisplayName("When the program is run, unique records should be added to a separate collection.")
    public void uniqueRecordGetsAddedToDifferentCollection(){
        EmployeeCsvDataValidator dataValidator = new EmployeeCsvDataValidator();
        dataValidator.setData(CSVReader.readCsvFile("TestEmployeeRecords.csv"));
        dataValidator.setUniqueAndDuplicate();
        assertNotNull(dataValidator.getUniqueData());
    }

    @Test
    @DisplayName("When the program is run, duplicated records should be added to a separate collection.")
    public void duplicatedRecordGetsAddedToDifferentCollection(){
        EmployeeCsvDataValidator dataValidator = new EmployeeCsvDataValidator();
        dataValidator.setData(CSVReader.readCsvFile("TestEmployeeRecords.csv"));
        dataValidator.setUniqueAndDuplicate();
        assertNotNull(dataValidator.getDuplicatedData());
    }

    @Test
    @DisplayName("When the program is run, missingValue records should be added to a separate collection.")
    public void missingValuesRecordGetsAddedToDifferentCollection(){
        EmployeeCsvDataValidator dataValidator = new EmployeeCsvDataValidator();
        dataValidator.setData(CSVReader.readCsvFile("TestEmployeeRecords.csv"));
        dataValidator.setMissingValuesData();
        assertNotNull(dataValidator.getMissingValuesData());
    }

    @Test
    @DisplayName("When there is corrupted data in the file, only valid data should be in the valid data collection.")
    public void returnValidRecords() {
        EmployeeCsvDataValidator dataValidator = new EmployeeCsvDataValidator();
        EmployeeCsvDataValidator test = new EmployeeCsvDataValidator();
        dataValidator.setData(CSVReader.readCsvFile("TestEmployeeRecords.csv"));
        test.setData(CSVReader.readCsvFile("TestValidData.csv"));
        dataValidator.splitData();
        ArrayList<String[]> actual = new ArrayList<>();
        ArrayList<String[]> expected = new ArrayList<>();
        for(String[] testRecord: test.getData()){
            expected.add(testRecord);
        }
        for(String[] record: dataValidator.getValid()){
            actual.add(record);
        }

        assertEquals(Arrays.deepToString(expected.toArray()), Arrays.deepToString(actual.toArray()));
    }

    @Test
    @DisplayName("When there is corrupted data in the file, only corrupted data should be in the corrupted data collection.")
    public void returnCorruptedRecords() {
        EmployeeCsvDataValidator dataValidator = new EmployeeCsvDataValidator();
        EmployeeCsvDataValidator test = new EmployeeCsvDataValidator();
        dataValidator.setData(CSVReader.readCsvFile("TestEmployeeRecords.csv"));
        test.setData(CSVReader.readCsvFile("TestCorruptedData.csv"));
        dataValidator.splitData();
        ArrayList<String[]> actual = new ArrayList<>();
        ArrayList<String[]> expected = new ArrayList<>();
        for(String[] testRecord: test.getData()){
            expected.add(testRecord);
        }
        for(String[] record: dataValidator.getCorrupted()){
            actual.add(record);
        }
        assertEquals(Arrays.deepToString(expected.toArray()), Arrays.deepToString(actual.toArray()));
    }

    @Test
    @DisplayName("When there is unique data in the file, only unique data should be in the unique data collection.")
    public void returnUniqueRecords() {
        EmployeeCsvDataValidator dataValidator = new EmployeeCsvDataValidator();
        EmployeeCsvDataValidator test = new EmployeeCsvDataValidator();
        dataValidator.setData(CSVReader.readCsvFile("TestEmployeeRecords.csv"));
        test.setData(CSVReader.readCsvFile("TestUniqueData.csv"));
        dataValidator.setUniqueAndDuplicate();
        ArrayList<String[]> actual = new ArrayList<>();
        ArrayList<String[]> expected = new ArrayList<>();
        for(String[] testRecord: test.getData()){
            expected.add(testRecord);
        }
        for(String[] record: dataValidator.getUniqueData()){
            actual.add(record);
        }
        assertEquals(Arrays.deepToString(expected.toArray()), Arrays.deepToString(actual.toArray()));
    }

    @Test
    @DisplayName("When there is duplicated data in the file, only duplicated data should be in the duplicated data collection.")
    public void returnDuplicatedRecords() {
        EmployeeCsvDataValidator dataValidator = new EmployeeCsvDataValidator();
        EmployeeCsvDataValidator test = new EmployeeCsvDataValidator();
        dataValidator.setData(CSVReader.readCsvFile("TestEmployeeRecords.csv"));
        test.setData(CSVReader.readCsvFile("TestDuplicatedData.csv"));
        dataValidator.setUniqueAndDuplicate();
        ArrayList<String[]> actual = new ArrayList<>();
        ArrayList<String[]> expected = new ArrayList<>();
        for(String[] testRecord: test.getData()){
            expected.add(testRecord);
        }
        for(String[] record: dataValidator.getDuplicatedData()){
            actual.add(record);
        }
        assertEquals(Arrays.deepToString(expected.toArray()), Arrays.deepToString(actual.toArray()));
    }

    @Test
    @DisplayName("When there is data with missing values in the file, only that data should be in the missing values data collection.")
    public void returnMissingValuesRecords() {
        EmployeeCsvDataValidator dataValidator = new EmployeeCsvDataValidator();
        EmployeeCsvDataValidator test = new EmployeeCsvDataValidator();
        dataValidator.setData(CSVReader.readCsvFile("TestEmployeeRecords.csv"));
        test.setData(CSVReader.readCsvFile("TestMissingValuesData.csv"));
        dataValidator.setMissingValuesData();
        ArrayList<String[]> actual = new ArrayList<>();
        ArrayList<String[]> expected = new ArrayList<>();
        for(String[] testRecord: test.getData()){
            expected.add(testRecord);
        }
        for(String[] record: dataValidator.getMissingValuesData()){
            actual.add(record);
        }
        assertEquals(Arrays.deepToString(expected.toArray()), Arrays.deepToString(actual.toArray()));
    }
}

//    @Test
//    @DisplayName("Testing that Merge Sort can correctly sort through an array not generated by Number Array")
//    public void positiveMergeTest() {
//        int[] actual = { 6, 2, 5, 1, 4, 3 };
//        int[] expected = { 1, 2, 3, 4, 5, 6 };
//        mergeSort(actual, actual.length);
//        assertArrayEquals(expected, actual);
//    }

    // Any corrupt or duplicated data should be added to a separate collection for further analysis.
    // Consider which date class would be best to use for the date fields – there is one in java.util and another in java.sql.
    // Provide a simple user interface to display the results of reading the file – how many unique, clean records there are, how many duplicates, how many records with missing fields, possibly display the questionable records.
    // Since the overall purpose of the project is data migration, we want to make sure that only clean data is transferred.



