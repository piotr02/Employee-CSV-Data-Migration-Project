package model;

import java.util.ArrayList;
import java.util.Arrays;

public class EmployeeDateConversion {
    private ArrayList<String[]> uniqueValidRecords;
    public EmployeeDateConversion(ArrayList<String[]> uniqueValidRecords){
        this.uniqueValidRecords = uniqueValidRecords;
    }

    public ArrayList<String[]> toSqlReadyRecords(){
        ArrayList<String[]> sqlReadyRecords = new ArrayList<>();

        for(int i = 0; i < this.uniqueValidRecords.size(); i++){
            String[] record = Arrays.copyOf(uniqueValidRecords.get(i), uniqueValidRecords.get(i).length);
            String dateOfJoining = record[EmployeeCsvDataValidatorNew.Field.DateOfJoining.getIndex()];
            String dateOfBirth = record[EmployeeCsvDataValidatorNew.Field.DateOfBirth.getIndex()];
            String[] dateMonthYear = dateOfJoining.split("/");
            record[EmployeeCsvDataValidatorNew.Field.DateOfJoining.getIndex()] = String.format("%s-%s-%s",dateMonthYear[2],  dateMonthYear[0],dateMonthYear[1]);

            dateMonthYear = dateOfBirth.split("/");
            dateOfBirth.split("/");
            record[EmployeeCsvDataValidatorNew.Field.DateOfBirth.getIndex()] = String.format("%s-%s-%s",dateMonthYear[2], dateMonthYear[0],dateMonthYear[1] );
            sqlReadyRecords.add(record);
        }
        return sqlReadyRecords;
    }
}
