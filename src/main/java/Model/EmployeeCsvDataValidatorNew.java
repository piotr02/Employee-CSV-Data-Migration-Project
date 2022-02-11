package model;

import model.validator.*;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;


public class EmployeeCsvDataValidatorNew{

    private ArrayList<String[]> cleanedData;

    //uniqueCleanRecords Should be added to the database
    private ArrayList<String[]> uniqueCleanRecords;
    private ArrayList<String[]> recordsWithMissingFields;
    private ArrayList<String[]> recordsWithDuplicatedId;
    private ArrayList<String[]> recordsWithIncorrectFields;
    private String[][] data;


    public EmployeeCsvDataValidatorNew(String[][] data){
        this.cleanData();
        this.catagorise();
    }

    public  EmployeeCsvDataValidatorNew(){

    }

    public void cleanData() {

        for (int i = 0; i < data.length; i++) {
            this.cleanedData.add(data[i]);
        }
        this.cleanedData.forEach(values ->{
            for(int i = 0; i < values.length; i++){
                values[i] = values[i].trim();
            }
        });
    }

    public void catagorise(){
        HashSet<String> existingIds = new HashSet<>();
        this.recordsWithMissingFields = new ArrayList<>();
        this.recordsWithDuplicatedId = new ArrayList<>();
        this.uniqueCleanRecords = new ArrayList<>();
        this.recordsWithIncorrectFields = new ArrayList<>();

        for(int i = 0; i < this.cleanedData.size(); i++){
            String[] record = this.cleanedData.get(i);
            boolean uniqueCleanRecord = true;
            if(isFieldsMissing(record)){
                this.recordsWithMissingFields.add(record);
                uniqueCleanRecord = false;
            }
            if(this.isRecordIdDuplicated(record, existingIds)){
                this.recordsWithDuplicatedId.add(record);
                uniqueCleanRecord = false;
            }
            if(this.isRecordContainingIncorrectFields(record)){
                this.recordsWithIncorrectFields.add(record);
                uniqueCleanRecord = false;
            }
            if(uniqueCleanRecord){
                this.uniqueCleanRecords.add(record);

                existingIds.add(record[0]);
            }
        }
    }

    //Emp ID,Name Prefix,First Name,Middle Initial,Last Name,Gender,E Mail,Date of Birth,Date of Joining,Salary



    public boolean isRecordContainingIncorrectFields(String[] record) {
        if(record == null) return true;
        String id = record[0];
        String prefix = record[1];
        String firstName = record[2];
        String middleInitial = record[3];
        String lastName = record[4];
        String gender = record[5];
        String email = record[6];
        String dateOfBirth = record[7];
        String dateOfJoining = record[8];
        String salary = record[9];

        String[] recordString = {id, prefix, firstName, middleInitial, lastName, gender,
        email, dateOfBirth, dateOfJoining, salary};


        boolean isValid = true;
        isValid = isValid &&
                new IntegerValidator().validate(id) &&
                new StringLengthValidator(10).validate(prefix) &&
                new StringEndsWithValidator(".").validate(prefix) &&
                new StringLengthValidator(50).validate(firstName) &&
                new StringLengthValidator(1).validate(middleInitial) &&
                new StringLengthValidator(50).validate(lastName) &&
                new GenderValidator().validate(gender) &&
                new EmailValidator().validate(email) &&
                new DateValidator("MM/dd/yyyy").validate(dateOfBirth) &&
                new DateValidator("MM/dd/yyyy").validate(dateOfJoining) &&
                new IntegerValidator().validate(salary);
        return !isValid;
    }


    private boolean isFieldsMissing(String[] row){
        if (row == null) return true;
        if (row.length < 10) return true;
        for(int i = 0; i < 10; i++){
            if(row[i].trim().equals("")){
               return true;
            }
        }
        return false;
    }

    private boolean isRecordIdDuplicated(String[] row, HashSet<String> existingIds){
        if (row == null) return false;

        if(existingIds.contains(row[0])){
            return true;
        }
        return false;
    }

    public ArrayList<String[]> getCleanedData() {
        return cleanedData;
    }

    public ArrayList<String[]> getRecordsWithMissingFields() {
        return recordsWithMissingFields;
    }

    public ArrayList<String[]> getRecordsWithDuplicatedId() {
        return recordsWithDuplicatedId;
    }

    public ArrayList<String[]> getRecordsWithIncorrectFields() {
        return recordsWithIncorrectFields;
    }

    public ArrayList<String[]> getUniqueCleanRecords() {
        return uniqueCleanRecords;
    }

}
