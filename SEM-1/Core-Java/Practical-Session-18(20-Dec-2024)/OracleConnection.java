import java.sql.*;

class OracleConnection {
    public static void main(String[] args) throws ClassNotFoundException {
        try {
            Connection connection = null;
            String serverName = "172.16.20.20";
            String portNumber = "1521";
            String sid = "orcl";

            String url = "jdbc:oracle:thin:@//" + serverName + ":" + portNumber + "/" + sid;

            String username = "C##mcaorc54";
            String password = "mcaorc54";

            connection = DriverManager.getConnection(url, username, password);

            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM emp2");

            ResultSetMetaData rsMetaData = rs.getMetaData();
            int columnCount = rsMetaData.getColumnCount();

            System.out.println("+------------------------------------------------+");
            for (int i = 1; i <= columnCount; i++) {
                System.out.printf("| %-10s ", rsMetaData.getColumnLabel(i));
            }
            System.out.println("|");
            System.out.println("+------------------------------------------------+");

            while (rs.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    System.out.printf("| %-10s ", rs.getString(i));
                }
                System.out.println("|");
            }

            System.out.println("+------------------------------------------------+");

        } catch (SQLException e) {
            throw new RuntimeException("Database error: " + e.getMessage(), e);
        }
    }
}