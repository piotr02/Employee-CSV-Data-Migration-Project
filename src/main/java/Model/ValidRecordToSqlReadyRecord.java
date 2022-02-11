package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;

public class ValidRecordToSqlReadyRecord {
    private ArrayList<String[]> uniqueValidRecords;
    ValidRecordToSqlReadyRecord(ArrayList<String[]> uniqueValidRecords){
        this.uniqueValidRecords = uniqueValidRecords;
    }

    public ArrayList<String[]> toSqlReadyRecords(){
        ArrayList<String[]> sqlReadyRecords = new ArrayList<>();
        SimpleDateFormat fromRecordString = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat forSql = new SimpleDateFormat("yyyy-MM-dd");

        for(String[] record : uniqueValidRecords){
            String[] sqlReadyRecord = Arrays.copyOf(record, record.length);
            try {
                sqlReadyRecord[EmployeeCsvDataValidatorNew.Field.DateOfJoining.getIndex()] = forSql.format(fromRecordString.parse(record[EmployeeCsvDataValidatorNew.Field.DateOfJoining.getIndex()]));
                sqlReadyRecord[EmployeeCsvDataValidatorNew.Field.DateOfBirth.getIndex()] = forSql.format(fromRecordString.parse(record[EmployeeCsvDataValidatorNew.Field.DateOfBirth.getIndex()]));
                uniqueValidRecords.add(sqlReadyRecord);
            }catch (ParseException e){
                e.printStackTrace();
            }
        }
        return sqlReadyRecords;
    }
}
