package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.sql.Date;
import java.util.ArrayList;

import static java.lang.System.nanoTime;
import static model.EmployeeDB.*;


public class ThreadTests {

    @Test
    @DisplayName("Given EmployeeRecords, write these contents to the database using 8 threads")
    void runEightThreadsThroughADatabase(){
        EmployeeDB employeeDb = new EmployeeDB();
        String[][] csvData = CSVReader.readCsvFile("EmployeeRecords.csv");
        EmployeeCsvDataValidatorNew dataValidator = new EmployeeCsvDataValidatorNew(csvData);
        ArrayList<String[]> sqlReadyRecords = new EmployeeDateConversion(dataValidator.getUniqueCleanRecords()).toSqlReadyRecords();
        ArrayList<Employee> employeeRecords = new RecordsToEmployee(sqlReadyRecords).getEmployeeArrayListFunctional();

        createDatabase();
        EmployeeDB.insertEmployeesThreaded(employeeRecords, 8);
    }

    @Test
    @DisplayName("Given EmployeeRecords, write these contents to the database using 1 thread")
    void runOneThreadThroughADatabase(){
        EmployeeDB employeeDb = new EmployeeDB();
        String[][] csvData = CSVReader.readCsvFile("TestEmployeeRecords.csv");
        EmployeeCsvDataValidatorNew dataValidator = new EmployeeCsvDataValidatorNew(csvData);
        ArrayList<String[]> sqlReadyRecords = new EmployeeDateConversion(dataValidator.getUniqueCleanRecords()).toSqlReadyRecords();
        ArrayList<Employee> employeeRecords = new RecordsToEmployee(sqlReadyRecords).getEmployeeArrayListFunctional();

        createDatabase();
        EmployeeDB.insertEmployeesThreaded(employeeRecords, 1);
    }

    @Test
    @DisplayName("Given EmployeeRecords, write these contents to the database using 0 threads")
    void runZeroThreadsThroughADatabase(){

        EmployeeDB employeeDb = new EmployeeDB();
        String[][] csvData = CSVReader.readCsvFile("EmployeeRecords.csv");
        EmployeeCsvDataValidatorNew dataValidator = new EmployeeCsvDataValidatorNew(csvData);
        ArrayList<String[]> sqlReadyRecords = new EmployeeDateConversion(dataValidator.getUniqueCleanRecords()).toSqlReadyRecords();
        ArrayList<Employee> employeeRecords = new RecordsToEmployee(sqlReadyRecords).getEmployeeArrayListFunctional();

        createDatabase();
        EmployeeDB.insertEmployeesThreaded(employeeRecords, 0);
    }

    @Test
    @DisplayName("Given EmployeeRecords, write these contents to the database using -1 threads")
    void runMinusOneThreadsThroughADatabase(){

        EmployeeDB employeeDb = new EmployeeDB();
        String[][] csvData = CSVReader.readCsvFile("EmployeeRecords.csv");
        EmployeeCsvDataValidatorNew dataValidator = new EmployeeCsvDataValidatorNew(csvData);
        ArrayList<String[]> sqlReadyRecords = new EmployeeDateConversion(dataValidator.getUniqueCleanRecords()).toSqlReadyRecords();
        ArrayList<Employee> employeeRecords = new RecordsToEmployee(sqlReadyRecords).getEmployeeArrayListFunctional();

        createDatabase();
        EmployeeDB.insertEmployeesThreaded(employeeRecords, -1);
    }

    @Test
    @DisplayName("Given EmployeeRecords, write these contents to the database using Integer.MAX_VALUE+1 threads")
    void runMaxIntegerPlusOneThreadsThroughADatabase(){

        EmployeeDB employeeDb = new EmployeeDB();
        String[][] csvData = CSVReader.readCsvFile("EmployeeRecords.csv");
        EmployeeCsvDataValidatorNew dataValidator = new EmployeeCsvDataValidatorNew(csvData);
        ArrayList<String[]> sqlReadyRecords = new EmployeeDateConversion(dataValidator.getUniqueCleanRecords()).toSqlReadyRecords();
        ArrayList<Employee> employeeRecords = new RecordsToEmployee(sqlReadyRecords).getEmployeeArrayListFunctional();

        createDatabase();
        EmployeeDB.insertEmployeesThreaded(employeeRecords, Integer.MAX_VALUE+1);
    }


//    @Test
//    @DisplayName("Create a the database known as Employee and try to add a single entry inside of it. Attempt to Display this record")
//    void enterItemIntoDatabase(){
//
//        createDatabase();
//
//        Date dob = new Date(02 / 04 / 1998);
//        Date join = new Date(03 / 07 / 2003);
//        insertEmployee(123, "Mr", "Bob", 'F', "Smith", 'M',
//                "bob@gmail.com", dob, join, 120000);
//
//        selectAllRecords();
//
//
//    }






}
