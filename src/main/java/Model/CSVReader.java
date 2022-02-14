package model;

import view.CSVView;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class CSVReader implements CSVTool {

    public CSVReader() {
        System.out.println("");
        System.out.println("=== ========= CHOOSE EMPLOYEE FILE TO ADD TO READ ========= ===");
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
        System.out.println("\n=== Reading Done\n");

        CSVView view = new CSVView();
        view.printRecords(csvData);
    }

    public static String[][] readCsvFile(String filename) {
        String[][] stringArray  = new String[0][];
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
          StringBuilder sb = new StringBuilder();
          String line;
          while ((line = br.readLine()) != null){
              sb.append(line);
              sb.append("\n");
          }
          String[] recordString = sb.toString().split("\n");
          stringArray = new String[recordString.length][];
          for(int i = 0; i < recordString.length; i++){
              stringArray[i] = recordString[i].split(",");
          }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringArray;
    }

    @Override
    public String validate() {
        return "";
    }
}
