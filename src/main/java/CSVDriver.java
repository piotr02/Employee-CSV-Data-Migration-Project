
import model.*;
import view.CSVView;

import java.sql.SQLException;
import java.util.ArrayList;

import static java.lang.System.nanoTime;

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


        //Populate with 1 thread
        EmployeeDB.createDatabase();
        long startTime = nanoTime();
        EmployeeDB.insertEmployeesThreaded(employeeRecords, 12);
        long endTime = nanoTime();
        System.out.println("With 1 theads it took: " + (endTime - startTime)  + "ns to write employees");

        //Populate with 8 threads
        EmployeeDB.createDatabase();
        startTime = nanoTime();
        EmployeeDB.insertEmployeesThreaded(employeeRecords, 8);
        endTime = nanoTime();
        System.out.println("With 8 theads it took: " + (endTime - startTime) + "ns to write employees");



        CSVView view = new CSVView();
        RecordCounter counter = new RecordCounter();
        ;

        //System.out.println(view.getValid(counter.countClean(dataValidator.getUniqueCleanRecords())));

        //EmployeeDB.selectEmployee(111800);
        EmployeeDB.selectAllRecords();
    }



}
