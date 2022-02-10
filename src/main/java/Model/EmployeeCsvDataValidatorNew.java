package model;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;


public class EmployeeCsvDataValidatorNew /*extends AbstractDataValidator implements CSVTool */{

    private ArrayList<String[]> cleanedData;
    private ArrayList<String[]> uniqueCleanRecords;
    private ArrayList<String[]> recordsWithMissingFields;
    private ArrayList<String[]> recordsWithDuplicatedId;
    private ArrayList<String[]> recordsWithIncorrectFields;
    private String[][] data;


    public EmployeeCsvDataValidatorNew(String[][] data){
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


    private boolean isEmployeeIdValid(String employeeId){
        try {
            Integer.parseInt(employeeId);
            return true;
        }catch (final NumberFormatException e){
            return false;
        }
    }

    private boolean isPrefixValid(String prefix){
        return prefix.endsWith(".");
    }

    private boolean isFirstNameValid(String firstName){
        return firstName.length() < 50;
    }

    private boolean isMiddleInitialValid(String middleInitial){
        return middleInitial.length() == 1;
    }

    private boolean isLastNameValid(String lastName){

        return lastName.length() < 50;
    }

    private boolean isGenderFieldValid(String data){
        return data.equals("M") || data.equals("F") || data.equals("X");
    }

    private boolean isEmailValid(String email){
        //The regex for an email
        return email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
    }

    private boolean isDateOfBirthValid(String dateOfBirth){

        SimpleDateFormat fromRecordString = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date.valueOf(String.valueOf(fromRecordString.parse(dateOfBirth)));
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    private boolean isDateOfJoiningValid(String dateOfJoining){
        SimpleDateFormat fromRecordString = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date.valueOf(String.valueOf(fromRecordString.parse(dateOfJoining)));
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    private boolean isSalaryValid(String salary){
        try {
            Integer.parseInt(salary);
            return true;
        }catch (final NumberFormatException e){
            return false;
        }
    }


    private boolean isRecordContainingIncorrectFields(String[] record) {
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

        boolean isValid = true;
        isValid = isValid &&
                isEmployeeIdValid(id) &&
                isPrefixValid(prefix) &&
                isFirstNameValid(firstName) &&
                isMiddleInitialValid(middleInitial) &&
                isLastNameValid(lastName) &&
                isGenderFieldValid(gender) &&
                isEmailValid(email) &&
                isDateOfBirthValid(dateOfBirth) &&
                isDateOfJoiningValid(dateOfJoining) &&
                isSalaryValid(salary);

        return !isValid;
    }


    private boolean isFieldsMissing(String[] row){
        if (row.length < 10) return true;
        for(int i = 0; i < 10; i++){
            if(row[i].trim().equals("")){
               return true;
            }
        }
        return false;
    }

    private boolean isRecordIdDuplicated(String[] row, HashSet<String> existingIds){
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
