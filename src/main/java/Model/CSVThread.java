//package model;
//
//import java.io.IOException;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.ArrayList;
//
//public class CSVThread extends Thread {
//    private String name;
//    private ArrayList<Employee> employeeList;
////    private Employee employee;
//
//    public CSVThread(String name, ArrayList<Employee> employeeList){
//        this.name = name;
//        this.employeeList = employeeList;
//    }
//
//    /**
//     * Populates the database with values from the list.
//     */
//    public void populateDatabase(){
//        for(int i = 0; i < employeeList.size(); i++) {
//            synchronized (employeeList.get(i)) {
//                EmployeeDB.insertEmployee(employeeList.get(i).employer_ID,
//                        employeeList.get(i).prefix,
//                        employeeList.get(i).firstName,
//                        employeeList.get(i).middleInitial,
//                        employeeList.get(i).lastName,
//                        employeeList.get(i).gender,
//                        employeeList.get(i).email,
//                        employeeList.get(i).dateOfBirth,
//                        employeeList.get(i).dateOfJoining,
//                        employeeList.get(i).salary);
//            }
//        }
////        EmployeeDB.insertEmployee(employee.employer_ID,
////                employee.prefix,
////                employee.firstName,
////                employee.middleInitial,
////                employee.lastName,
////                employee.gender,
////                employee.email,
////                employee.dateOfBirth,
////                employee.dateOfJoining,
////                employee.salary);
//        System.out.println(name + " finished.");
//    }
//
//    /**
//     * Overrides the default run() method from class Thread to populate the database.
//     */
//    @Override
//    public void run(){
//        populateDatabase();
//    }
//}
