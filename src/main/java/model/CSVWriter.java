package model;

import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.System.currentTimeMillis;

public class CSVWriter implements CSVTool{

    public CSVWriter(){
        System.out.println("");
        System.out.println("=== ======= CHOOSE EMPLOYEE FILE TO ADD TO DATABASE ======= ===");
        System.out.println("\n\t EmployeeRecordsLarge \n\t EmployeeRecords");

        System.out.println("");

        String csvFileName = null;

        while (csvFileName == null) {
            Scanner scanner = new Scanner(System.in);
            String chosen = scanner.next();
            switch (chosen.toLowerCase()) {
                case "employeerecordslarge" -> csvFileName = "EmployeeRecordsLarge.csv";
                case "employeerecords" -> csvFileName = "EmployeeRecords.csv";
                default -> System.out.println("Invalid File Option");
            }
            System.out.println("=== Chosen file: " + csvFileName);

        }

        System.out.println("\n=== ================= READING CHOSEN CSV ================== ===");
        String[][] csvData = CSVReader.readCsvFile("EmployeeRecords.csv");
        System.out.println("\n=== Reading Done");
        System.out.println("\n=== =============== SEPARATING CORRUPT DATA =============== ===");
        EmployeeCsvDataValidatorNew dataValidator = new EmployeeCsvDataValidatorNew(csvData);
        System.out.println("\n=== Separating corrupt data done");
        System.out.println("\n=== ============ CONVERTING DATES TO SQL FORMAT =========== ===");
        ArrayList<String[]> sqlReadyRecords = new EmployeeDateConversion(dataValidator.getUniqueCleanRecords()).toSqlReadyRecords();
        System.out.println("\n=== Converting done");
        System.out.println("\n=== ================= GETTING VALID DATA ================== ===");
        ArrayList<Employee> employeeRecords = new RecordsToEmployee(sqlReadyRecords).getEmployeeArrayListFunctional();
        System.out.println("\n=== Getting valid data done");

        EmployeeDB employeeDb = new EmployeeDB();

        boolean addedToDatabase = false;
        while (!addedToDatabase) {
            long startTime;
            long endTime;

            System.out.println("\n=== ======= CHOOSE HOW TO INSERT DATA INTO DATABASE ======= ===\n" +
                    "\tthreadSpeedTest\n" +
                    "\tstream\n" +
                    "\tparallelStream\n" +
                    "\tthread <int:threadCount>");
            Scanner scanner = new Scanner(System.in);
            String command = scanner.nextLine().toLowerCase();

            System.out.println("=== ========== INSERTING EMPLOYEES INTO DATABASE ========== ===");

            if(command.equals("threadspeedtest")) {
                System.out.println("=== ============= WAIT: INSERTING WITH THREADS ============ ===");
                int[] threadCounts = {1, 4, 8, 10, 12, 16, 20};
                for (int count: threadCounts) {
                    EmployeeDB.createDatabase();
                    startTime = currentTimeMillis();
                    EmployeeDB.insertEmployeesThreaded(employeeRecords, count);
                    endTime = currentTimeMillis();
                    System.out.println("\n=== With "+ count + " thread it took: " + (endTime - startTime) + " milliseconds to write employees");

                }

                System.out.println("");
                System.out.println("=== ============= WAIT: INSERTING WITH STREAM ============= ===");
                System.out.println("");

                EmployeeDB.createDatabase();
                startTime = currentTimeMillis();
                EmployeeDB.insertEmployeesStream(employeeRecords);
                endTime = currentTimeMillis();
                System.out.println("=== With stream it took: " + (endTime - startTime) + " milliseconds to write employees");

                System.out.println("");
                System.out.println("=== ========= WAIT: INSERTING WITH PARALLEL STREAM ======== ===");
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
                System.out.println("\n=== With stream it took: " + (endTime - startTime) + " milliseconds to write employees");
                addedToDatabase = true;
            }else if(command.equals("parallelstream")){
                EmployeeDB.createDatabase();
                startTime = currentTimeMillis();
                EmployeeDB.insertEmployeesStreamParallel(employeeRecords);
                endTime = currentTimeMillis();
                System.out.println("\n=== With parallel stream it took: " + (endTime - startTime) + " milliseconds to write employees");
                addedToDatabase = true;
            }else {
                String[] threadCommand = command.split(" ");
                if(threadCommand[0].trim().equals("thread")){
                    int threadCount = Integer.parseInt(threadCommand[1]);
                    EmployeeDB.createDatabase();
                    startTime = currentTimeMillis();
                    EmployeeDB.insertEmployeesThreaded(employeeRecords, threadCount);
                    endTime = currentTimeMillis();
                    System.out.println("\n=== With "+ threadCount +" threads it took: " + (endTime - startTime) + " milliseconds to write employees");
                    addedToDatabase = true;
                }
            }
            if(!addedToDatabase){
                System.out.println("Invalid Input");
            }
        }

        boolean shouldContinue = false;
        do {
            System.out.println("\n===   CHOOSE AN OPTION TO DISPLAY AN EMPLOYEE OR CONTINUE   ===\n" +
                    "\tall\n" +
                    "\tselect <int:employeeId>\n" +
                    "\tcontinue");

            Scanner scanner = new Scanner(System.in);
            String command = scanner.nextLine().toLowerCase();
            if(command.trim().equals("all")){
                EmployeeDB.selectAllRecords();
            }else if(command.trim().equals("continue")){
                shouldContinue = true;
            }else{
                String[] commandStrings = command.split(" ");
                String action = commandStrings[0].trim();
                String param = commandStrings[1].trim();
                if(action.equals("select")){
                    EmployeeDB.selectEmployee(Integer.parseInt(param));
                }
            }
        }while (!shouldContinue);
    }

    @Override
    public String validate() {
        return "";
    }
}
