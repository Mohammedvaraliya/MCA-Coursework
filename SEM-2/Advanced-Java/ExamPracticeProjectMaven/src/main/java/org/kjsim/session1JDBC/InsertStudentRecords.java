// Write a java program to connect with the MySQL database & insert n number of records in ___ table using prepareStatement.

package org.kjsim.session1JDBC;

import java.sql.*;
import java.util.Scanner;

public class InsertStudentRecords {
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/mcadb202426";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    public static void main(String[] args) {
        Connection conn = null;

        String query = "insert into students (rollno, email, course, city, reg_time) values(?,?,?,?,?)";

        try {
            conn = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
            System.out.println("Connected to database successfully!");

            PreparedStatement pst = conn.prepareStatement(query);

            Scanner sc = new Scanner(System.in);

            System.out.println("Enter the number of records to insert into database: ");
            int num = sc.nextInt();
            sc.nextLine(); // consume new line

            for (int i = 0; i < num; i++) {
                System.out.print("Enter rollno: ");
                String rollno = sc.nextLine();
                System.out.print("Enter email: ");
                String email = sc.nextLine();
                System.out.print("Enter course: ");
                String course = sc.nextLine();
                System.out.print("Enter city: ");
                String city = sc.nextLine();
                System.out.print("Enter reg time (YYYY-MM-DD HH:MM:SS): ");
                String regTime = sc.nextLine();

                pst.setString(1, rollno);
                pst.setString(2, email);
                pst.setString(3, course);
                pst.setString(4, city);
                pst.setString(5, regTime);
                pst.executeUpdate();
                System.out.println("Record for Student " + i + " inserted successfully!");
            }

            pst.close();
            conn.close();
            sc.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
