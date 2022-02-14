package model;

import model.validator.*;
import view.CSVView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class EmployeeCsvDataValidatorNew extends AbstractDataValidator implements CSVTool{

    public EmployeeCsvDataValidatorNew() {
    }

    @Override
    public String validate() {
        System.out.println();
        System.out.println("=== ============== CHOOSE FILE TO VALIDATE ================ ===");
        System.out.println("\n\t EmployeeRecordsLarge \n\t EmployeeRecords");
        System.out.println();

        String csvFileName = null;

        while (csvFileName == null) {
            Scanner scanner = new Scanner(System.in);
            String chosen = scanner.next();
            switch (chosen.toLowerCase()) {
                case "employeerecordslarge" -> csvFileName = "EmployeeRecordsLarge.csv";
                case "employeerecords" -> csvFileName = "EmployeeRecords.csv";
                default -> System.out.println("Invalid File Option");
            }
            System.out.println("=== Chosen file: " + csvFileName);
        }

        System.out.println("\n=== ================ VALIDATING CHOSEN CSV ================ ===");
        System.out.println("\n=== Validating done\n");

        RecordCounter counter = new RecordCounter();
        CSVView view = new CSVView();
        int cleanCount = counter.countClean(getCleanedData());
        int uniqueCleanCount = counter.countUniqueClean(getUniqueCleanRecords());
        int missingCount = counter.countMissingValuesRecords(getMissingValuesData());
        int duplicatedCount = counter.countDuplicated(getRecordsWithDuplicatedId());
        int incorrectCount = counter.countIncorrectValuesRecords(getRecordsWithIncorrectFields());
        view.printResult(cleanCount, uniqueCleanCount, missingCount, duplicatedCount, incorrectCount);
        return "";
    }

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
        isValid &= new StringLengthValidator(100).validate(firstName);
        isValid &= new StringLengthValidator(1).validate(middleInitial);
        isValid &= new StringLengthValidator(100).validate(lastName);
        isValid &= new GenderValidator().validate(gender);
        isValid &= new EmailValidator().validate(email);
        isValid &= new StringLengthValidator(100).validate(email);
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
