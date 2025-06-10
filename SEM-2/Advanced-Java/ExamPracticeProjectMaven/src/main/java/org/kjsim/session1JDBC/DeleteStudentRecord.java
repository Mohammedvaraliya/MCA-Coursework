// Write a program to connect with the database & delete records using MySQL database.

package org.kjsim.session1JDBC;

import java.sql.*;
import java.util.Scanner;

public class DeleteStudentRecord {private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/mcadb202426";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    public static void main(String[] args) {
        Connection conn = null;

        String query = "DELETE FROM students WHERE rollno = ?";

        try {
            conn = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
            System.out.println("Connected to database successfully!");

            PreparedStatement pst = conn.prepareStatement(query);

            Scanner sc = new Scanner(System.in);

            System.out.println("Enter the roll number of the student you would like to delete: ");
            String rollnoToDelete = sc.nextLine();

            pst.setString(1, rollnoToDelete);

            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Record for student " + rollnoToDelete + " has been deleted successfully!");
            } else {
                System.out.println("Record for student " + rollnoToDelete + " not found!");
            }

            pst.close();
            conn.close();
            sc.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}