package model;

import java.util.ArrayList;

public class RecordCounter {
    public int countRecords(String[][] records){
        return records.length;
    }

    public int countUnique(ArrayList<String[]> uniqueRecords){
        return uniqueRecords.size();
    }

    public int countDuplicated(ArrayList<String[]> duplicatedRecords){
        return duplicatedRecords.size();
    }

    public int countMissingValuesRecords(ArrayList<String[]> missingValuesData){
        return missingValuesData.size();
    }

    public int countValid(ArrayList<String[]> validData){
        return validData.size();
    }

    public int countCorrupted(ArrayList<String[]> corruptedData){
        return corruptedData.size();
    }
}
