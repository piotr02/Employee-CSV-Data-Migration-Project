package Model;

import java.io.IOException;
import java.sql.*;

public class employeeDB {
    public static void main(String[] args) {


        useStatement();

        usePreparedStatement();

        insertPrepared();


    }

    private static void useStatement() {

        Statement statement = null;
        ResultSet rs = null;
        try {
            Connection connection = ConnectionFactory.getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery("SELECT * FROM employee_record");
            while (rs.next()) { // Whilst there is a next element in the collection, the loop will keep running.
                System.out.println(rs.getString("first_name") + " " + rs.getString("last_name"));
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




    private static void insertPrepared() {

        PreparedStatement preparedStatement = null;
        try {
            Connection connection = ConnectionFactory.getConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO employee_record (idemployee_id,first_name, last_name,email,date,time) VALUES (?,?,?,?,?,?)");
            preparedStatement.setString(1, "4");
            preparedStatement.setString(2, "Aiden");
            preparedStatement.setString(3, "Sykes");
            preparedStatement.setString(4, "aiden.sykes@gmail.com");
            preparedStatement.setString(5, "2021/01/11");
            preparedStatement.setString(6, "10.00.00");
            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println(rowsAffected);
            preparedStatement.close();
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

    private static void usePreparedStatement() {

        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try {
            Connection connection = ConnectionFactory.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM employee_record WHERE last_name=?");
            preparedStatement.setString(1, "dhamale"); // Placeholders
            rs = preparedStatement.executeQuery();
            while (rs.next()) { // Whilst there is a next element in the collection, the loop will keep running.
                System.out.println(rs.getString("first_name") + " " + rs.getString("last_name"));
            }
            rs.close();
            preparedStatement.close();
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