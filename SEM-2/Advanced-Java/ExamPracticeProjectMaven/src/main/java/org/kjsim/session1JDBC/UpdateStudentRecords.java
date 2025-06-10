// Write a java program to connect with the database & update records using MySQL database.

package org.kjsim.session1JDBC;

import java.sql.*;
import java.util.Scanner;

public class UpdateStudentRecords {
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/mcadb202426";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    public static void main(String[] args) {
        Connection conn = null;

        String query = "UPDATE students SET email = ?, course = ?, city = ?, reg_time = ? WHERE rollno = ?";

        try {
            conn = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
            System.out.println("Connected to database successfully!");

            PreparedStatement pst = conn.prepareStatement(query);

            Scanner sc = new Scanner(System.in);

            System.out.println("Enter the roll number of the student you would like to update: ");
            String rollnoToUpdate = sc.nextLine();

            System.out.print("Enter new email: ");
            String email = sc.nextLine();
            System.out.print("Enter new course: ");
            String course = sc.nextLine();
            System.out.print("Enter new city: ");
            String city = sc.nextLine();
            System.out.print("Enter new reg time (YYYY-MM-DD HH:MM:SS): ");
            String regTime = sc.nextLine();

            pst.setString(1, email);
            pst.setString(2, course);
            pst.setString(3, city);
            pst.setString(4, regTime);
            pst.setString(5, rollnoToUpdate);

            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Record for student " + rollnoToUpdate + " updated successfully!");
            } else {
                System.out.println("Record for student " + rollnoToUpdate + " not found!");
            }

            pst.close();
            conn.close();
            sc.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}