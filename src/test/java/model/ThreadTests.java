package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
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
}
