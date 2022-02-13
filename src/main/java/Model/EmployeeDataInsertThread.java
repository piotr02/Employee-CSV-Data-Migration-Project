package model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class EmployeeDataInsertThread implements Runnable {

    private int startIndex;
    private int stride;
    private int rowsAffected;
    private ArrayList<Employee> employeeCollection;
    public EmployeeDataInsertThread(ArrayList<Employee> employeeCollection, int startIndex, int stride){
        this.employeeCollection = employeeCollection;
        this.startIndex = startIndex;
        this.stride = stride;
        this.rowsAffected = 0;
    }
    @Override
    public void run() {
        insertByStride();
    }

    public int getRowsAffected() {
        return rowsAffected;
    }

    public void insertByStride(){
        int currentIndex = this.startIndex;
        PreparedStatement preparedStatement = null;
        try {
            Connection connection = ConnectionFactory.getConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO Employee (Emp_ID, Name_Prefix, First_Name, Middle_Initial, Last_Name, Gender, Email, Date_Of_Birth, Date_Of_Joining, Salary) VALUES (?,?,?,?,?,?,?,?,?,?)");
            while (currentIndex < employeeCollection.size()){
                Employee employee = this.employeeCollection.get(currentIndex);

                preparedStatement.setString(1, ""+employee.employer_ID);
                preparedStatement.setString(2, employee.prefix);
                preparedStatement.setString(3, employee.firstName);
                preparedStatement.setString(4, ""+employee.middleInitial);
                preparedStatement.setString(5, employee.lastName);
                preparedStatement.setString(6, ""+employee.gender);
                preparedStatement.setString(7, employee.email);
                preparedStatement.setString(8, ""+employee.dateOfBirth);
                preparedStatement.setString(9, ""+employee.dateOfJoining);
                preparedStatement.setString(10, ""+employee.salary);
                int rowsAffected = preparedStatement.executeUpdate();
                currentIndex += this.stride;
                this.rowsAffected += rowsAffected;
            }
            preparedStatement.close();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }

    }
}
