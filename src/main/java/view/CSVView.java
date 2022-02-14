package view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import static view.CSVView.CSV.*;

public class CSVView {
    public enum CSV {
        READ("read"), VALIDATE("validate"), WRITE("write");

        private final String choice;

        CSV(String name) {
            this.choice = name;
        }

        public String getCSV() { return getCSV(); }

    }

    public String csvSelector() {


        boolean validEnum = false;
        String selectCSV = null;

        while (validEnum == false) {
            System.out.println("Which feature would you like to use?");
            Scanner scanner = new Scanner(System.in);
            selectCSV = scanner.next().toLowerCase();

            if (selectCSV.equals(READ.choice) || selectCSV.equals(VALIDATE.choice)|| selectCSV.equals(WRITE.choice)) {
                validEnum = true;

            }

            else {
                validEnum = false;
            }
        }
        return selectCSV;
    }

    public void displayCSV(String result) {

        System.out.println(result);

    }

    // Prints the records from the file line by line
    public void printRecords(String[][] records){
        System.out.println("All records from the file: ");
        for(String[] record: records){
            System.out.println(Arrays.deepToString(record));
        }
    }

    // Prints clean records
    public void printCleanedData(ArrayList<String[]> cleanedData){
        System.out.println("Cleaned records from the file: ");
        for(String[] cleaned: cleanedData){
            System.out.println(Arrays.deepToString(cleaned));
        }
    }

    // Prints unique cleaned records
    public void printUniqueCleanedData(ArrayList<String[]> uniqueCleanedData){
        System.out.println("Unique cleaned records from the file: ");
        for(String[] uniqueCleaned: uniqueCleanedData){
            System.out.println(Arrays.deepToString(uniqueCleaned));
        }
    }

    // Prints records with missing fields
    public void printRecordsWithMissingFields(ArrayList<String[]> recordsWithMissingFields){
        System.out.println("Records with missing fields from the file: ");
        for(String[] record: recordsWithMissingFields){
            System.out.println(Arrays.deepToString(record));
        }
    }

    // Prints records with incorrect fields
    public void printRecordsWithIncorrectFields(ArrayList<String[]> recordsWithIncorrectFields){
        System.out.println("Records with missing fields from the file: ");
        for(String[] record: recordsWithIncorrectFields){
            System.out.println(Arrays.deepToString(record));
        }
    }

    // Prints duplicated records
    public void printDuplicatedRecords(ArrayList<String[]> duplicatedRecords){
        System.out.println("Records with missing fields from the file: ");
        for(String[] record: duplicatedRecords){
            System.out.println(Arrays.deepToString(record));
        }
    }

    // Prints the number of cleaned records in the file
    public void printCleanedDataRows(int cleanedRows){
        System.out.println("=== Number of cleaned records in the file: " + cleanedRows);
    }

    // Prints the number of unique cleaned records in the file
    public void printUniqueCleanedRows(int uniqueCleanedRows){
        System.out.println("=== Number of unique cleaned records in the file: " + uniqueCleanedRows);

    }

    // Prints the number of records with missing fields in the file
    public void printRecordsWithMissingFieldsRows(int recordsWithMissingFieldsRows){
        System.out.println("=== Number of records with missing fields in the file: " + recordsWithMissingFieldsRows);
    }

    // Prints the number of duplicate records in the file
    public void printDuplicatesRows(int duplicatesRows){
        System.out.println("=== Number of duplicate records in the file: " + duplicatesRows);
    }

    // Prints the number of records with incorrect fields in the file
    public void printRecordsWithIncorrectFieldsRows(int recordsWithIncorrectFieldsRows){
        System.out.println("=== Number of records with incorrect fields in the file: " + recordsWithIncorrectFieldsRows);
    }

    // prints start interface
    public void printStart(){
        System.out.println("=== ======================================================= ===");
        System.out.println("===           EMPLOYEE CSV DATA MIGRATION PROJECT           ===");
        System.out.println("=== ======================================================= ===");
        System.out.println("");
        System.out.println("=== ======================= READ CSV ====================== ===");
        System.out.println("");
    }

    // Prints the results interface
    public void printResult(int cleaned, int uniqueCleaned, int missing, int duplicates, int incorrect){
        System.out.println("=== ======================= RESULTS ======================= ===");
        System.out.println();
        printCleanedDataRows(cleaned);
        printUniqueCleanedRows(uniqueCleaned);
        printDuplicatesRows(duplicates);
        printRecordsWithMissingFieldsRows(missing);
        printRecordsWithIncorrectFieldsRows(incorrect);
    }
}
