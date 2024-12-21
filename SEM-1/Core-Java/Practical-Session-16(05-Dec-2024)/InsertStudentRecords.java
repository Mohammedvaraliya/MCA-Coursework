import java.sql.*;
import java.util.Scanner;

public class InsertStudentRecords {
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/mcadb202426";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    public static void main(String[] args) {
        Connection connection = null;

        String query = "INSERT INTO students (rollno, email, course, city, reg_time) VALUES (?, ?, ?, ?, ?)";

        try {
            connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
            System.out.println("Connected to the database successfully!");

            PreparedStatement pst = connection.prepareStatement(query);

            Scanner sc = new Scanner(System.in);

            System.out.print("Enter the number of records to insert: ");
            int n = sc.nextInt();
            sc.nextLine(); // Consume the leftover newline

            for (int i = 1; i <= n; i++) {
                System.out.println("Enter details for student " + i + ":");

                System.out.print("Roll Number: ");
                String rollno = sc.nextLine();

                System.out.print("Email: ");
                String email = sc.nextLine();

                System.out.print("Course: ");
                String course = sc.nextLine();

                System.out.print("City: ");
                String city = sc.nextLine();

                System.out.print("Registration Time (YYYY-MM-DD HH:MM:SS): ");
                String regTime = sc.nextLine();

                pst.setString(1, rollno);
                pst.setString(2, email);
                pst.setString(3, course);
                pst.setString(4, city);
                pst.setString(5, regTime);

                pst.executeUpdate();
                System.out.println("Record for student " + i + " inserted successfully.");
            }
            sc.close();
            System.out.println("All records inserted.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}