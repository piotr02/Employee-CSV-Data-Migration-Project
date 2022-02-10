package view;

import java.util.ArrayList;
import java.util.Arrays;

public class CSVView {
    // Prints the records from the file line by line
    public void printRecords(String[][] records){
        System.out.println("All records from the file: ");
        for(String[] record: records){
            System.out.println(Arrays.deepToString(record));
        }
    }

    // Prints valid records
    public void printValid(ArrayList<String[]> validData){
        System.out.println("Valid records from the file: ");
        for(String[] validRecord: validData){
            System.out.println(Arrays.deepToString(validRecord));
        }
    }

    // Prints corrupted records
    public void printCorrupted(ArrayList<String[]> corruptedData){
        System.out.println("Corrupted records from the file: ");
        for(String[] corruptedRecord: corruptedData){
            System.out.println(Arrays.deepToString(corruptedRecord));
        }
    }

    // Returns the number of unique records in the file
    public String getUnique(int unique){
        return "Number of unique records in the file: " + unique;
    }

    // Returns the number of valid records in the file
    public String getValid(int valid){
        return "Number of valid records in the file: " + valid;
    }

    // Returns the number of corrupted records in the file
    public String getCorrupted(int corrupted){
        return "Number of corrupted records in the file: " + corrupted;
    }

    // Returns the number of duplicate records in the file
    public String getDuplicates(int duplicates){
        return "Number of duplicate records in the file: " + duplicates;
    }

    // Returns the number of missing fields in the file
    public String getMissingFields(int missingFields){
        return "Number of records with missing fields in the file: " + missingFields;
    }

    // Returns questionable records from the file
    public String qetQuestionableRecords(String questionableRecords){
        return "List of questionable records: " + questionableRecords;
    }

    // Prints the results interface
    public void printResult(int unique, int valid, int corrupted, int duplicates, int missingFields, String questionableRecords){
        System.out.println("==================================================\n" +
                "Employee CSV Data Migration Project\n" +
                "==================================================\n +" +
                getUnique(unique)+
                "\n--------------------------------------------------\n"+
                getValid(valid)+
                "\n--------------------------------------------------\n"+
                getCorrupted(corrupted)+
                "\n--------------------------------------------------\n"+
                getDuplicates(duplicates)+
                "\n--------------------------------------------------\n"+
                getMissingFields(missingFields)+
                "\n--------------------------------------------------");
    }
}
