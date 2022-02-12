
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


        EmployeeDB employeeDb = new EmployeeDB();

        System.out.println("");
        System.out.println("============ Drop Table if nessessary ===============");
        System.out.println("");

        System.out.println("");
        System.out.println("============ Create table if exists ===============");
        System.out.println("");

        System.out.println("");
        System.out.println("============ Use prepared statement to insert Employees===============");
        System.out.println("");

        System.out.println("");
        System.out.println("============ Insert Each Employees to Database (With Multi Threading)===============");
        System.out.println("");


        CSVView view = new CSVView();
        //RecordCounter counter = new RecordCounter();
    }



}
