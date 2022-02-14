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
        ArrayList<Thread> threads = new ArrayList<>();
        for(int i = 0; i < threadCount; i++){
            Thread thread = new Thread(new EmployeeDataInsertThread(employees, i, threadCount));
            threads.add(thread);
        }
        for(int i = 0; i < threadCount; i++){
            threads.get(i).start();
        }

        for(int i = 0; i < threadCount; i++){
            try {
                threads.get(i).join();
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



    public static void insertEmployeesStreamParallel(ArrayList<Employee> employeeRecords) {
        try {
            Connection connection = ConnectionFactory.getConnection();
            employeeRecords.stream().parallel().forEach(employee -> {
                try {
                    PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Employee (Emp_ID, Name_Prefix, First_Name, Middle_Initial, Last_Name, Gender, Email, Date_Of_Birth, Date_Of_Joining, Salary) VALUES (?,?,?,?,?,?,?,?,?,?)");
                    preparedStatement.setInt(1, employee.employer_ID);
                    preparedStatement.setString(2, employee.prefix);
                    preparedStatement.setString(3, employee.firstName);
                    preparedStatement.setString(4, String.valueOf(employee.middleInitial));
                    preparedStatement.setString(5, employee.lastName);
                    preparedStatement.setString(6, String.valueOf(employee.gender));
                    preparedStatement.setString(7, employee.email);
                    preparedStatement.setDate(8, employee.dateOfBirth);
                    preparedStatement.setDate(9, employee.dateOfJoining);
                    preparedStatement.setInt(10, employee.salary);
                    preparedStatement.executeUpdate();
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });
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

    public static void insertEmployeesStream(ArrayList<Employee> employeeRecords) {
        try {
            Connection connection = ConnectionFactory.getConnection();
            employeeRecords.stream().forEach(employee -> {
                try {
                    PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Employee (Emp_ID, Name_Prefix, First_Name, Middle_Initial, Last_Name, Gender, Email, Date_Of_Birth, Date_Of_Joining, Salary) VALUES (?,?,?,?,?,?,?,?,?,?)");
                    preparedStatement.setInt(1, employee.employer_ID);
                    preparedStatement.setString(2, employee.prefix);
                    preparedStatement.setString(3, employee.firstName);
                    preparedStatement.setString(4, String.valueOf(employee.middleInitial));
                    preparedStatement.setString(5, employee.lastName);
                    preparedStatement.setString(6, String.valueOf(employee.gender));
                    preparedStatement.setString(7, employee.email);
                    preparedStatement.setDate(8, employee.dateOfBirth);
                    preparedStatement.setDate(9, employee.dateOfJoining);
                    preparedStatement.setInt(10, employee.salary);
                    preparedStatement.executeUpdate();
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });
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
}