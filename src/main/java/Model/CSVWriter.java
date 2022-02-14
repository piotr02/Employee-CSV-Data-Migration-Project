package model;

import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.System.currentTimeMillis;

public class CSVWriter implements CSVTool{

    public CSVWriter(){
        System.out.println("");
        System.out.println("============= Choose EmployeeFile To Add To Database ======================");
        System.out.println("\n\t employeeCsvLarge \n\t employeeCsv");

        System.out.println("");

        String csvFileName = null;

        while (csvFileName == null) {
            Scanner scanner = new Scanner(System.in);
            String chosen = scanner.next();
            switch (chosen.toLowerCase()) {
                case "employeecsvlarge" -> csvFileName = "EmployeeRecordsLarge.csv";
                case "employeecsv" -> csvFileName = "EmployeeRecords.csv";
                default -> System.out.println("Invalid File Option");
            }
            System.out.println(csvFileName);

        }

        System.out.println("============= Reading Chosen CSV ======================");
        String[][] csvData = CSVReader.readCsvFile("EmployeeRecords.csv");
        System.out.println("============ Seperate Corrupt data ===============");
        EmployeeCsvDataValidatorNew dataValidator = new EmployeeCsvDataValidatorNew(csvData);
        System.out.println("============ Getting The Valid Data ===============");
        ArrayList<String[]> sqlReadyRecords = new EmployeeDateConversion(dataValidator.getUniqueCleanRecords()).toSqlReadyRecords();
        System.out.println("============ Convert Dates to SQL Format ===============");

        ArrayList<Employee> employeeRecords = new RecordsToEmployee(sqlReadyRecords).getEmployeeArrayListFunctional();
        EmployeeDB employeeDb = new EmployeeDB();

        boolean addedToDatabase = false;
        while (!addedToDatabase) {
            long startTime;
            long endTime;
            //Populate with 1 thread
            System.out.println("Choose how to insert data into database \n" +
                    "\tthreadSpeedTest\n" +
                    "\tstream\n" +
                    "\tparallelStream\n" +
                    "thread <int:threadCount>");
            Scanner scanner = new Scanner(System.in);
            String command = scanner.nextLine().toLowerCase();
            System.out.println("============ WAIT: Inserting Employees into database ===============");

            if(command.equals("threadspeedtest")) {

                int[] threadCounts = {1, 4, 8, 10, 12, 16, 20};
                for (int count: threadCounts) {
                    EmployeeDB.createDatabase();
                    startTime = currentTimeMillis();
                    EmployeeDB.insertEmployeesThreaded(employeeRecords, count);
                    endTime = currentTimeMillis();
                    System.out.println("=== With "+ count + " thread it took: " + (endTime - startTime) + " milliseconds to write employees");

                }

                System.out.println("");
                System.out.println("============ WAIT ===============");
                System.out.println("");

                EmployeeDB.createDatabase();
                startTime = currentTimeMillis();
                EmployeeDB.insertEmployeesStream(employeeRecords);
                endTime = currentTimeMillis();
                System.out.println("With stream it took: " + (endTime - startTime) + " milliseconds to write employees");

                System.out.println("");
                System.out.println("============ WAIT ===============");
                System.out.println("");

                EmployeeDB.createDatabase();
                startTime = currentTimeMillis();
                EmployeeDB.insertEmployeesStreamParallel(employeeRecords);
                endTime = currentTimeMillis();
                System.out.println("=== With parallel stream it took: " + (endTime - startTime) + " milliseconds to write employees");
                addedToDatabase = true;
            }else if(command.equals("stream")) {
                EmployeeDB.createDatabase();
                startTime = currentTimeMillis();
                EmployeeDB.insertEmployeesStream(employeeRecords);
                endTime = currentTimeMillis();
                System.out.println("=== With stream it took: " + (endTime - startTime) + " milliseconds to write employees");
                addedToDatabase = true;
            }else if(command.equals("parallelstream")){
                EmployeeDB.createDatabase();
                startTime = currentTimeMillis();
                EmployeeDB.insertEmployeesStreamParallel(employeeRecords);
                endTime = currentTimeMillis();
                System.out.println("=== With parallel stream it took: " + (endTime - startTime) + " milliseconds to write employees");
                addedToDatabase = true;
            }else {
                String[] threadCommand = command.split(" ");
                if(threadCommand[0].trim().equals("thread")){
                    int threadCount = Integer.parseInt(threadCommand[1]);
                    EmployeeDB.createDatabase();
                    startTime = currentTimeMillis();
                    EmployeeDB.insertEmployeesThreaded(employeeRecords, threadCount);
                    endTime = currentTimeMillis();
                    System.out.println("=== With "+ threadCount +" threads it took: " + (endTime - startTime) + " milliseconds to write employees");
                    addedToDatabase = true;
                }
            }
            if(!addedToDatabase){
                System.out.println("Invalid Input");
            }
        }
    }

    @Override
    public String validate() {
        return "";
    }
}