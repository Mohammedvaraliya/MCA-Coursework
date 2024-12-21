import java.sql.*;
import java.util.Scanner;

public class DeleteStudentRecord {
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/mcadb202426";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    public static void main(String[] args) {
        Connection connection = null;

        String query = "DELETE FROM students WHERE rollno = ?";

        try {
            connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
            System.out.println("Connected to the database successfully!");

            PreparedStatement pst = connection.prepareStatement(query);

            Scanner sc = new Scanner(System.in);

            System.out.print("Enter the roll number of the student to delete: ");
            String rollnoToDelete = sc.nextLine();

            pst.setString(1, rollnoToDelete);

            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Record with roll number " + rollnoToDelete + " deleted successfully.");
            } else {
                System.out.println("No record found with roll number " + rollnoToDelete + ".");
            }

            sc.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}