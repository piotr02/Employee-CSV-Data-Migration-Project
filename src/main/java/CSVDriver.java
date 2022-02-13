
import model.*;
import view.CSVView;

import java.sql.SQLException;
import java.util.ArrayList;

import static java.lang.System.console;
import static java.lang.System.currentTimeMillis;

public class CSVDriver {


    public static void main(String[] args) {
        CSVView view = new CSVView();
        view.printStart();
        String[][] csvData = CSVReader.readCsvFile("EmployeeRecords.csv");

        EmployeeCsvDataValidatorNew dataValidator = new EmployeeCsvDataValidatorNew(csvData);

        ArrayList<String[]> sqlReadyRecords = new EmployeeDateConversion(dataValidator.getUniqueCleanRecords()).toSqlReadyRecords();

        ArrayList<Employee> employeeRecords = new RecordsToEmployee(sqlReadyRecords).getEmployeeArrayListFunctional();

        EmployeeDB employeeDb = new EmployeeDB();

        //Populate with 1 thread
        EmployeeDB.createDatabase();
        long startTime = currentTimeMillis();
        EmployeeDB.insertEmployeesThreaded(employeeRecords, 1);
        long endTime = currentTimeMillis();
        System.out.println("=== With 1 thread it took: " + (endTime - startTime)  + " milliseconds to write employees");

        EmployeeDB.createDatabase();
        startTime = currentTimeMillis();
        EmployeeDB.insertEmployeesThreaded(employeeRecords, 4);
        endTime = currentTimeMillis();
        System.out.println("=== With 4 threads it took: " + (endTime - startTime)  + " milliseconds to write employees");

        EmployeeDB.createDatabase();
         startTime = currentTimeMillis();
        EmployeeDB.insertEmployeesThreaded(employeeRecords, 8);
         endTime = currentTimeMillis();
        System.out.println("=== With 8 threads it took: " + (endTime - startTime)  + " milliseconds to write employees");

        EmployeeDB.createDatabase();
        startTime = currentTimeMillis();
        EmployeeDB.insertEmployeesThreaded(employeeRecords, 10);
        endTime = currentTimeMillis();
        System.out.println("=== With 10 threads it took: " + (endTime - startTime)  + " milliseconds to write employees");

        EmployeeDB.createDatabase();
        startTime = currentTimeMillis();
        EmployeeDB.insertEmployeesThreaded(employeeRecords, 12);
        endTime = currentTimeMillis();
        System.out.println("=== With 12 threads it took: " + (endTime - startTime)  + " milliseconds to write employees");

        //Populate with 8 threads
        EmployeeDB.createDatabase();
        startTime = currentTimeMillis();
        EmployeeDB.insertEmployeesThreaded(employeeRecords, 16);
        endTime = currentTimeMillis();
        System.out.println("=== With 16 theads it took: " + (endTime - startTime) + " milliseconds to write employees");

        EmployeeDB.createDatabase();
        startTime = currentTimeMillis();
        EmployeeDB.insertEmployeesThreaded(employeeRecords, 20);
        endTime = currentTimeMillis();
        System.out.println("=== With 20 theads it took: " + (endTime - startTime) + " milliseconds to write employees");

        EmployeeDB.createDatabase();
        startTime = currentTimeMillis();
        EmployeeDB.insertEmployeesStream(employeeRecords);
        endTime = currentTimeMillis();
        System.out.println("=== With stream it took: " + (endTime - startTime) + " milliseconds to write employees");

        EmployeeDB.createDatabase();
        startTime = currentTimeMillis();
        EmployeeDB.insertEmployeesStreamParallel(employeeRecords);
        endTime = currentTimeMillis();
        System.out.println("=== With parallel stream it took: " + (endTime - startTime) + " milliseconds to write employees");

        RecordCounter counter = new RecordCounter();

        int cleanCount = counter.countClean(dataValidator.getCleanedData());
        int uniqueCleanCount = counter.countUniqueClean(dataValidator.getUniqueCleanRecords());
        int missingCount = counter.countMissingValuesRecords(dataValidator.getMissingValuesData());
        int duplicatedCount = counter.countDuplicated(dataValidator.getRecordsWithDuplicatedId());
        int incorrectCount = counter.countIncorrectValuesRecords(dataValidator.getRecordsWithIncorrectFields());
        view.printResult(cleanCount, uniqueCleanCount, missingCount, duplicatedCount, incorrectCount);

    }



}
