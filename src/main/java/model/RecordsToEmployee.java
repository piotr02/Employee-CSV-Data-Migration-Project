package model;

import java.util.ArrayList;
import java.util.List;

public class RecordsToEmployee {
    private ArrayList<String[]> sqlReadyRecords;

    public RecordsToEmployee(ArrayList<String[]> sqlReadyRecords){
        this.sqlReadyRecords = sqlReadyRecords;
    }

    public ArrayList<Employee> getEmployeeArrayListImperative(){
        ArrayList<Employee> employeeArrayList = new ArrayList<>();
        for (String[] record : this.sqlReadyRecords) {
            Employee employee = new Employee();
            employee.employer_ID = Integer.parseInt(record[0]);
            employee.prefix = record[1];
            employee.firstName = record[2];
            employee.middleInitial = record[3].charAt(0);
            employee.lastName = record[4];
            employee.gender = record[5].charAt(0);
            employee.email = record[6];
            employee.dateOfBirth = java.sql.Date.valueOf(record[7]);
            employee.dateOfJoining = java.sql.Date.valueOf(record[8]);
            employee.salary = Integer.parseInt(record[9]);
            employeeArrayList.add(employee);
        }
        return  employeeArrayList;
    }

    public ArrayList<Employee> getEmployeeArrayListFunctional(){
        List<Employee> employeeList = this.sqlReadyRecords.stream().map(record ->{
            Employee employee = new Employee();
            employee.employer_ID = Integer.parseInt(record[0]);
            employee.prefix = record[1];
            employee.firstName = record[2];
            employee.middleInitial = record[3].charAt(0);
            employee.lastName = record[4];
            employee.gender = record[5].charAt(0);
            employee.email = record[6];
            employee.dateOfBirth = java.sql.Date.valueOf(record[7]);
            employee.dateOfJoining = java.sql.Date.valueOf(record[8]);
            employee.salary = Integer.parseInt(record[9]);
            return employee;
        }).toList();
        return new ArrayList<>(employeeList);
    }
}
