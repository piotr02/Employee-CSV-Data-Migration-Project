
import model.*;
import view.CSVView;

import java.util.ArrayList;

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


        System.out.println("");
        System.out.println("============ Insert Employees to Database (With Multi Threading)===============");
        System.out.println("");

        CSVView view = new CSVView();
        //RecordCounter counter = new RecordCounter();
    }



}
