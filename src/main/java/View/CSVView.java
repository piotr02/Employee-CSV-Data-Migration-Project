package View;

import java.util.Arrays;

public class CSVView {
    // Prints the records from the file line by line
    public void printRecords(String[][] records){
        System.out.println("All records from the file: ");
        for(String[] record: records){
            System.out.println(Arrays.deepToString(record));
        }
    }

    // Prints clean records
    public void printClean(String[][] cleanRecords){
        System.out.println("Clean records from the file: ");
        for(String[] cleanRecord: cleanRecords){
            System.out.println(Arrays.deepToString(cleanRecord));
        }
    }

    // Returns the number of unique records in the file
    public String getUnique(int unique){
        return "Number of unique records in the file: " + unique;
    }

    // Returns the number of clean records in the file
    public String getClean(int clean){
        return "Number of clean records in the file: " + clean;
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
    public void printResult(int unique, int clean, int duplicates, int missingFields, String questionableRecords){
        System.out.println("==================================================\n" +
                "Employee CSV Data Migration Project\n" +
                "==================================================\n +" +
                getUnique(unique)+
                "\n--------------------------------------------------\n"+
                getClean(clean)+
                "\n--------------------------------------------------\n"+
                getDuplicates(duplicates)+
                "\n--------------------------------------------------\n"+
                getMissingFields(missingFields)+
                "\n--------------------------------------------------");
    }
}
