package model;

import java.util.HashSet;


public class EmployeeCsvDataValidator extends AbstractDataValidator{
    private HashSet<String> existingIds;

    public EmployeeCsvDataValidator(String[][] data) {
        super(data);
    }

    public EmployeeCsvDataValidator(){
        super();
    }

    /**
     * This seperates the Corrupted Data from the valid data*/
    @Override
    public void splitData() {
        super.splitData();
        this.existingIds = new HashSet<>();
        String[][] data = this.getData();
        for(int i = 0; i < getData().length; i++){
            String[] row = data[i];
            if(isEmployeeRowCorrupt(row, this.existingIds)){
                this.corruptedData.add(data[i]);
            }else {
                this.existingIds.add(row[0]);
                this.validData.add(row);
            }
        }
    }

    /**
     * This is the method that determines if a row (The data for a single employee) in the String[][] 2D array is corrupted or valid*/
    private boolean isEmployeeRowCorrupt(String[] row, HashSet<String> existingIds){

        if(row.length != 10) return true;


        String id = row[0];
        String middleInitial = row[3];
        String gender = row[5];
        String salary = row[9];

        // If ID isn't numeric, add to Corrupt Array file.
        if(id.matches("[a-zA-Z]+")){
            return true;
        }
        if(existingIds.contains(id)){
            return true;
        }

        //   If Middle initial not 1 character long
        if(!(middleInitial.length() == 1)){
            return true;
        }
        //   If Gender not in M, F
        if(!gender.matches(("^M$|^F$"))){
            return true;
        }
        //   If Salary includes anything but numbers
        if(salary.matches("[a-zA-Z]+")){
            return true;
        }

        // if name prefix not in enum?

        // If date of birth/date of join don't adhere to date format

        return false;
    }

}
