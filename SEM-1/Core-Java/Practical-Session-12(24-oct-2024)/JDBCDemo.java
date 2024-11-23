import java.sql.*;

public class JDBCDemo {
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/test";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    public static void main(String[] args) {
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
            System.out.println("Connected to the database successfully!");

            String sql = "SELECT * FROM studentDetail";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            System.out.printf("%-20s %-15s %-15s %-10s%n", "Student Name", "Roll No", "Phone No", "Gender");
            System.out.println("-------------------------------------------------------");

            while (resultSet.next()) {
                String name = resultSet.getString("student_name");
                int rollNo = resultSet.getInt("student_roll_no");
                String phoneNo = resultSet.getString("student_phone_no");
                String gender = resultSet.getString("student_gender");

                System.out.printf("%-20s %-15d %-15s %-10s%n", name, rollNo, phoneNo, gender);
            }

            resultSet.close();
            statement.close();

        } catch (SQLException e) {
            System.err.println("Connection failed!");
            e.printStackTrace();
        } finally {
            // Close the connection
            if (connection != null) {
                try {
                    connection.close();
                    System.out.println("Connection closed.");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}