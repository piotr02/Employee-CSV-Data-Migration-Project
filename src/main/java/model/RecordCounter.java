package model;

import java.util.ArrayList;

public class RecordCounter {
    public int countRecords(String[][] records){
        return records.length;
    }

    public int countUniqueClean(ArrayList<String[]> uniqueRecords){
        if (uniqueRecords == null){
            return 0;
        }
        else {
            return uniqueRecords.size();
        }
    }

    public int countDuplicated(ArrayList<String[]> duplicatedRecords){
        if (duplicatedRecords == null){
            return 0;
        }
        else {
            return duplicatedRecords.size();
        }
    }

    public int countMissingValuesRecords(ArrayList<String[]> missingValuesData){
        if (missingValuesData == null){
            return 0;
        }
        else {
            return missingValuesData.size();
        }
    }

    public int countClean(ArrayList<String[]> cleanData){
        if (cleanData == null){
            return 0;
        }
        else {
            return cleanData.size();
        }
    }

    public int countIncorrectValuesRecords(ArrayList<String[]> incorrectValues){
        if (incorrectValues == null){
            return 0;
        }
        else {
            return incorrectValues.size();
        }
    }
}