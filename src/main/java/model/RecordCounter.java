package model;

import java.util.ArrayList;

public class RecordCounter {
    public int countRecords(String[][] records){
        return records.length;
    }

    public int countUniqueClean(ArrayList<String[]> uniqueRecords){
        return uniqueRecords.size();
    }

    public int countDuplicated(ArrayList<String[]> duplicatedRecords){
        return duplicatedRecords.size();
    }

    public int countMissingValuesRecords(ArrayList<String[]> missingValuesData){
        return missingValuesData.size();
    }

    public int countClean(ArrayList<String[]> validData){
        return validData.size();
    }

    public int countIncorrectValuesRecords(ArrayList<String[]> incorrectValues){
        return incorrectValues.size();
    }
}