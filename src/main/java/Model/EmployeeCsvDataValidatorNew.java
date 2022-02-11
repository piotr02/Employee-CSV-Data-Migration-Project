package model;

import model.validator.*;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;


public class EmployeeCsvDataValidatorNew {

//    @Override
//    public String validate() {
//        return null;
//    }

    public enum Field{
        Id(0),
        Prefix(1),
        FirstName(2),
        MiddleInitial(3),
        LastName(4),
        Gender(5),
        Email(6),
        DateOfBirth(7),
        DateOfJoining(8),
        Salary(9);

        private int index;
        Field(int index){
            this.index = index;
        }

        public int getIndex() {
            return index;
        }
    }
    private ArrayList<String[]> cleanedData;

    //uniqueCleanRecords Should be added to the database
//    private ArrayList<String[]> uniqueCleanSqlReadyRecords;
    private ArrayList<String[]> uniqueCleanRecords;
    private ArrayList<String[]> recordsWithMissingFields;
    private ArrayList<String[]> recordsWithDuplicatedId;
    private ArrayList<String[]> recordsWithIncorrectFields;
    private String[][] data;


    public EmployeeCsvDataValidatorNew(String[][] data){
        this.data = Arrays.copyOfRange(data, 1, data.length);
        this.cleanedData = new ArrayList<>();
        this.uniqueCleanRecords = new ArrayList<>();
        this.recordsWithMissingFields = new ArrayList<>();
        this.recordsWithDuplicatedId = new ArrayList<>();
        this.recordsWithIncorrectFields = new ArrayList<>();
        this.cleanData();
        this.catagorise();
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
                this.recordsWithIncorrectFields.add(record);
            }else {
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
    }

    //Emp ID,Name Prefix,First Name,Middle Initial,Last Name,Gender,E Mail,Date of Birth,Date of Joining,Salary



    public boolean isRecordContainingIncorrectFields(String[] record) {
        if(record == null) return true;
        String id = record[Field.Id.index];
        String prefix = record[Field.Prefix.index];
        String firstName = record[Field.FirstName.index];
        String middleInitial = record[Field.MiddleInitial.index];
        String lastName = record[Field.LastName.index];
        String gender = record[Field.Gender.index];
        String email = record[Field.Email.index];
        String dateOfBirth = record[Field.DateOfBirth.index];
        String dateOfJoining = record[Field.DateOfJoining.index];
        String salary = record[Field.Salary.index];

        String[] recordString = {id, prefix, firstName, middleInitial, lastName, gender,
        email, dateOfBirth, dateOfJoining, salary};


        boolean isValid = new IntegerValidator().validate(id);
        isValid &= new StringLengthValidator(10).validate(prefix);
        isValid &= new StringEndsWithValidator(".").validate(prefix);
        isValid &= new StringLengthValidator(50).validate(firstName);
        isValid &= new StringLengthValidator(1).validate(middleInitial);
        isValid &= new StringLengthValidator(50).validate(lastName);
        isValid &= new GenderValidator().validate(gender);
        isValid &= new EmailValidator().validate(email);
        isValid &= new DateValidator("MM/dd/yyyy").validate(dateOfBirth);
        isValid &= new DateValidator("MM/dd/yyyy").validate(dateOfJoining);
        isValid &= new IntegerValidator().validate(salary);
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
