package model;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class EmployeeDB {
    public static void createDatabase(){
        Statement statement = null;
        try {
            Connection connection = ConnectionFactory.getConnection();
            statement = connection.createStatement();
            statement.execute("DROP DATABASE IF EXISTS EmployeeCSV");
            statement.execute("CREATE DATABASE EmployeeCSV");
            statement.execute("USE EmployeeCSV");
            statement.execute("CREATE TABLE IF NOT EXISTS Employee (" +
                    "Emp_ID integer PRIMARY KEY not null," +
                    "Name_Prefix varchar(10) not null," +
                    "First_Name varchar(100) not null," +
                    "Middle_Initial char not null," +
                    "Last_Name varchar (100) not null," +
                    "Gender char not null," +
                    "Email varchar (100) not null," +
                    "Date_Of_Birth Date not null," +
                    "Date_Of_Joining Date not null," +
                    "Salary integer not null)");
            statement.close();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        } finally {
            try {
                ConnectionFactory.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void selectAllRecords() {
        Statement statement = null;
        ResultSet rs = null;
        try {
            Connection connection = ConnectionFactory.getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery("SELECT * FROM Employee;");
            while (rs.next()) { // Whilst there is a next element in the collection, the loop will keep running.
                System.out.println("Emp_ID: " + rs.getString(1) + ", Name_Prefix: "
                        + rs.getString(2) + ", First_Name: "
                        + rs.getString(3) + ", Middle_Initial: "
                        + rs.getString(4) + ", Last_Name: "
                        + rs.getString(5) + ", Gender: "
                        + rs.getString(6) + ", Email: "
                        + rs.getString(7) + ", Date_Of_Birth: "
                        + rs.getString(8) + ", Date_Of_Joining: "
                        + rs.getString(9) + ", Salary: "
                        + rs.getString(10));
            }
            rs.close();
            statement.close();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        } finally {
            try {
                ConnectionFactory.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void selectEmployee(int employeeid) {
        Statement statement = null;
        ResultSet rs = null;
        try {
            Connection connection = ConnectionFactory.getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery("SELECT * FROM Employee WHERE Emp_ID =" +employeeid+";");
            if (rs.next()) { // Whilst there is a next element in the collection, the loop will keep running.
                System.out.println("Emp_ID: " + rs.getString(1) + ", Name_Prefix: "
                        + rs.getString(2) + ", First_Name: "
                        + rs.getString(3) + ", Middle_Initial: "
                        + rs.getString(4) + ", Last_Name: "
                        + rs.getString(5) + ", Gender: "
                        + rs.getString(6) + ", Email: "
                        + rs.getString(7) + ", Date_Of_Birth: "
                        + rs.getString(8) + ", Date_Of_Joining: "
                        + rs.getString(9) + ", Salary: "
                        + rs.getString(10));
            }
            else{
                System.out.println("Employee does not exist.");
            }
            rs.close();
            statement.close();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        } finally {
            try {
                ConnectionFactory.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void insertEmployeesThreaded(ArrayList<Employee> employees, int threadCount){
        //ArrayList<EmployeeDataInsertThread> employeeDataInsertThread = new ArrayList<>();
        ArrayList<Thread> threads = new ArrayList<>();
        for(int i = 0; i < threadCount; i++){
            //EmployeeDataInsertThread employeeThread = ;
            //employeeDataInsertThread.add(employeeThread);
            Thread thread = new Thread(new EmployeeDataInsertThread(employees, i, threadCount));
            threads.add(thread);
        }
        for(int i = 0; i < threadCount; i++){
            threads.get(i).start();
        }

        for(int i = 0; i < threadCount; i++){
            try {

                threads.get(i).join();
                //int totalRowsEffected = 0;
                //totalRowsEffected += employeeDataInsertThread.get(i).getRowsAffected();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        try {
            ConnectionFactory.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    public static void insertEmployee(int employeeId, String namePrefix, String firstName, char middleInitial,
//                                       String lastName, char gender, String email, Date dateOfBirth, Date dateOfJoining, int salary) {
//
//        PreparedStatement preparedStatement = null;
//        Statement statement = null;
//        try {
//            Connection connection = ConnectionFactory.getConnection();
//            statement = connection.createStatement();
//            statement.execute("USE EmployeeCSV");
//            preparedStatement = connection.prepareStatement("INSERT INTO Employee (Emp_ID, Name_Prefix, First_Name, Middle_Initial, Last_Name, Gender, Email, Date_Of_Birth, Date_Of_Joining, Salary) VALUES (?,?,?,?,?,?,?,?,?,?)");
//            preparedStatement.setString(1, ""+employeeId);
//            preparedStatement.setString(2, namePrefix);
//            preparedStatement.setString(3, firstName);
//            preparedStatement.setString(4, ""+middleInitial);
//            preparedStatement.setString(5, lastName);
//            preparedStatement.setString(6, ""+gender);
//            preparedStatement.setString(7, email);
//            preparedStatement.setString(8, ""+dateOfBirth);
//            preparedStatement.setString(9, ""+dateOfJoining);
//            preparedStatement.setString(10, ""+salary);
//
//            int rowsAffected = preparedStatement.executeUpdate();
//
//            System.out.println(rowsAffected);
//            preparedStatement.close();
//        } catch (SQLException | IOException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                ConnectionFactory.closeConnection();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }
}