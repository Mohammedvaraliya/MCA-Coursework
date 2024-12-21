import java.sql.*;
import java.util.Scanner;

public class UpdateStudentRecords {
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/mcadb202426";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    public static void main(String[] args) {
        Connection connection = null;

        String query = "UPDATE students SET rollno = ?, email = ?, course = ?, city = ?, reg_time = ? WHERE rollno = ?";

        try {
            connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
            System.out.println("Connected to the database successfully!");

            PreparedStatement pst = connection.prepareStatement(query);

            Scanner sc = new Scanner(System.in);

            System.out.print("Enter the roll number of the student to update: ");
            String rollnoToUpdate = sc.nextLine();

            System.out.print("Enter new Roll Number: ");
            String newRollno = sc.nextLine();

            System.out.print("Enter new Email: ");
            String newEmail = sc.nextLine();

            System.out.print("Enter new Course: ");
            String newCourse = sc.nextLine();

            System.out.print("Enter new City: ");
            String newCity = sc.nextLine();

            System.out.print("Enter new Registration Time (YYYY-MM-DD HH:MM:SS): ");
            String newRegTime = sc.nextLine();

            pst.setString(1, newRollno);
            pst.setString(2, newEmail);
            pst.setString(3, newCourse);
            pst.setString(4, newCity);
            pst.setString(5, newRegTime);
            pst.setString(6, rollnoToUpdate);

            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Record with roll number " + rollnoToUpdate + " updated successfully.");
            } else {
                System.out.println("No record found with roll number " + rollnoToUpdate + ".");
            }

            sc.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}