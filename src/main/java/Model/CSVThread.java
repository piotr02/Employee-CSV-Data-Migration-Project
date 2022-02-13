package model;

import java.util.ArrayList;

public class CSVThread extends Thread{
    private String name;
    private ArrayList<Employee> employeeList;

    public CSVThread(String name, ArrayList<Employee> employeeList){
        this.name = name;
        this.employeeList = employeeList;
    }

    /**
     * Populates the database with values from the list.
     */
    public void populateDatabase(){
            for(Employee employee : employeeList) {
                synchronized (this) {
                    EmployeeDB.insertEmployee(employee.employer_ID,
                             employee.prefix,
                             employee.firstName,
                             employee.middleInitial,
                             employee.lastName,
                             employee.gender,
                             employee.email,
                             employee.dateOfBirth,
                             employee.dateOfJoining,
                             employee.salary);
                }
            }
            System.out.println(name + " finished.");
    }

    /**
     * Overrides the default run() method from class Thread to populate the database.
     */
    @Override
    public void run(){
        populateDatabase();
    }
}
