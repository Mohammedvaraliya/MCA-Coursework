import java.sql.*;

public class RetrieveStudentRecords {
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/mcadb202426";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    public static void main(String[] args) {
        Connection connection = null;
        Statement stmt = null;
        ResultSet rs = null;

        String query = "SELECT * FROM students";

        try {
            connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
            System.out.println("Connected to the database successfully!");

            stmt = connection.createStatement();
            rs = stmt.executeQuery(query);

            if (!rs.next()) {
                System.out.println("No records found in the students table.");
                return;
            }

            System.out.printf("%-15s %-30s %-20s %-15s %-20s%n", "RollNo", "Email", "Course", "City", "Reg_Time");
            System.out.println(
                    "---------------------------------------------------------------------------------------------");

            do {
                String rollno = rs.getString("rollno");
                String email = rs.getString("email");
                String course = rs.getString("course");
                String city = rs.getString("city");
                String regTime = rs.getString("reg_time");

                System.out.printf("%-15s %-30s %-20s %-15s %-20s%n", rollno, email, course, city, regTime);
            } while (rs.next());

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (connection != null) {
                    connection.close();
                    System.out.println("Connection closed.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}