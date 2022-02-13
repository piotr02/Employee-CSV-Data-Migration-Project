
import model.*;
import view.CSVView;

import java.sql.SQLException;
import java.util.ArrayList;

import static java.lang.System.currentTimeMillis;

public class CSVDriver {


    public static void main(String[] args) {

        System.out.println("");
        System.out.println("============= Allow User To Choose A CSV File ======================");
        System.out.println("");


        System.out.println("");
        System.out.println("============= Read Chosen CSV ======================");
        System.out.println("");


        String[][] csvData = CSVReader.readCsvFile("EmployeeRecords.csv");

        System.out.println("");
        System.out.println("============ Seperate Corrupt ===============");
        System.out.println("");


        EmployeeCsvDataValidatorNew dataValidator = new EmployeeCsvDataValidatorNew(csvData);

        System.out.println("");
        System.out.println("============ Get The Valid Data ===============");
        System.out.println("");


        ArrayList<String[]> sqlReadyRecords = new EmployeeDateConversion(dataValidator.getUniqueCleanRecords()).toSqlReadyRecords();

        System.out.println("");
        System.out.println("============ Convert Dates in Valid Data ===============");
        System.out.println("");

        ArrayList<Employee> employeeRecords = new RecordsToEmployee(sqlReadyRecords).getEmployeeArrayListFunctional();


        EmployeeDB employeeDb = new EmployeeDB();

        System.out.println("");
        System.out.println("============ WAIT ===============");
        System.out.println("");


        //Populate with 1 thread
        EmployeeDB.createDatabase();
        long startTime = currentTimeMillis();
        EmployeeDB.insertEmployeesThreaded(employeeRecords, 1);
        long endTime = currentTimeMillis();
        System.out.println("With 1 thread it took: " + (endTime - startTime)  + " milliseconds to write employees");


        EmployeeDB.createDatabase();
        startTime = currentTimeMillis();
        EmployeeDB.insertEmployeesThreaded(employeeRecords, 4);
        endTime = currentTimeMillis();
        System.out.println("With 4 threads it took: " + (endTime - startTime)  + " milliseconds to write employees");


        EmployeeDB.createDatabase();
         startTime = currentTimeMillis();
        EmployeeDB.insertEmployeesThreaded(employeeRecords, 8);
         endTime = currentTimeMillis();
        System.out.println("With 8 threads it took: " + (endTime - startTime)  + " milliseconds to write employees");

        EmployeeDB.createDatabase();
        startTime = currentTimeMillis();
        EmployeeDB.insertEmployeesThreaded(employeeRecords, 10);
        endTime = currentTimeMillis();
        System.out.println("With 10 threads it took: " + (endTime - startTime)  + " milliseconds to write employees");

        EmployeeDB.createDatabase();
        startTime = currentTimeMillis();
        EmployeeDB.insertEmployeesThreaded(employeeRecords, 12);
        endTime = currentTimeMillis();
        System.out.println("With 12 threads it took: " + (endTime - startTime)  + " milliseconds to write employees");

        //Populate with 8 threads
        EmployeeDB.createDatabase();
        startTime = currentTimeMillis();
        EmployeeDB.insertEmployeesThreaded(employeeRecords, 16);
        endTime = currentTimeMillis();
        System.out.println("With 16 theads it took: " + (endTime - startTime) + " milliseconds to write employees");

        EmployeeDB.createDatabase();
        startTime = currentTimeMillis();
        EmployeeDB.insertEmployeesThreaded(employeeRecords, 20);
        endTime = currentTimeMillis();
        System.out.println("With 20 theads it took: " + (endTime - startTime) + " milliseconds to write employees");

        EmployeeDB.createDatabase();
        startTime = currentTimeMillis();
        EmployeeDB.insertEmployeesStream(employeeRecords);
        endTime = currentTimeMillis();
        System.out.println("With stream it took: " + (endTime - startTime) + " milliseconds to write employees");

        EmployeeDB.createDatabase();
        startTime = currentTimeMillis();
        EmployeeDB.insertEmployeesStreamParallel(employeeRecords);
        endTime = currentTimeMillis();
        System.out.println("With parallel stream it took: " + (endTime - startTime) + " milliseconds to write employees");




        CSVView view = new CSVView();
        RecordCounter counter = new RecordCounter();
        ;

        //System.out.println(view.getValid(counter.countClean(dataValidator.getUniqueCleanRecords())));

        //EmployeeDB.selectEmployee(111800);
        //EmployeeDB.selectAllRecords();
    }



}
