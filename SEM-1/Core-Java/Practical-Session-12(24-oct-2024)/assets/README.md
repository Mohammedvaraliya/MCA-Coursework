### **Create 4 database in `phpmyadmin` and insert 10 records at minimum.**

1. **Step 1: Create a Database in phpMyAdmin**
   1. **Log in to phpMyAdmin.**
   2. **Create a New Database:**
      1. Click on the "Databases" tab.
      2. In the "Create database" field, enter `test` (or another name).
      3. Click "Create".
1. **Step 2: Create Tables in the `test` Database**

   1. Create `studentDetail` Table

      1. **SQL Query:**

      ```sql
      CREATE TABLE studentDetail (
          student_name VARCHAR(100),
          student_roll_no INT PRIMARY KEY,
          student_phone_no VARCHAR(15),
          student_gender VARCHAR(10)
      );
      ```

      2. **Insert Records:**

      ```sql
      INSERT INTO courses (course_name, course_description, credits) VALUES
      ('Mathematics 101', 'Introduction to Mathematics', 60),
      ('Physics 101', 'Fundamentals of Physics', 70),
      ('Chemistry 101', 'Basics of Chemistry', 55),
      ('Biology 101', 'Introduction to Biology', 65),
      ('History 101', 'World History Overview', 50),
      ('Computer Science 101', 'Introduction to Computer Science', 75),
      ('Literature 101', 'Introduction to Literature', 62),
      ('Economics 101', 'Fundamentals of Economics', 58),
      ('Philosophy 101', 'Introduction to Philosophy', 67),
      ('Art 101', 'Introduction to Art and Design', 72);
      ```

   2. Create `faculty` Table

      1. **SQL Query:**

      ```sql
      CREATE TABLE faculty (
          faculty_id INT AUTO_INCREMENT PRIMARY KEY,
          faculty_name VARCHAR(100) NOT NULL,
          department VARCHAR(100),
          email VARCHAR(100) NOT NULL
      );
      ```

      2. **Insert Records:**

      ```sql
      INSERT INTO faculty (faculty_name, department, email) VALUES
      ('Sudarshan Sharma', 'Mathematics', 'sudarshan.sharma@example.com'),
      ('Krantee Desai', 'Physics', 'krantee.desai@example.com'),
      ('Sangeeta Reddy', 'Chemistry', 'sangeeta.reddy@example.com'),
      ('Jaya Singh', 'Biology', 'jaya.singh@example.com'),
      ('Mayur Patil', 'History', 'mayur.patil@example.com'),
      ('Anjali Gupta', 'Computer Science', 'anjali.gupta@example.com'),
      ('Rahul Mehta', 'Literature', 'rahul.mehta@example.com'),
      ('Priyanka Roy', 'Economics', 'priyanka.roy@example.com'),
      ('Ravi Kumar', 'Philosophy', 'ravi.kumar@example.com'),
      ('Sneha Verma', 'Art', 'sneha.verma@example.com');
      ```

   3. Create `enrollments` Table

      1. **SQL Query:**

      ```sql
      CREATE TABLE enrollments (
          enrollment_id INT AUTO_INCREMENT PRIMARY KEY,
          student_id INT NOT NULL,
          course_id INT NOT NULL,
          semester VARCHAR(10),
          grade CHAR(1),
          FOREIGN KEY (student_id) REFERENCES studentDetail(student_roll_no)
      );
      ```

      2. **Insert Records:**

      ```sql
      INSERT INTO enrollments (student_id, course_id, semester, grade) VALUES
      (54, 1, 'Fall 2024', 'A'),
      (53, 2, 'Fall 2024', 'B'),
      (1, 3, 'Spring 2024', 'A'),
      (2, 4, 'Spring 2024', 'C'),
      (3, 5, 'Fall 2024', 'B'),
      (4, 6, 'Fall 2024', 'A'),
      (5, 7, 'Spring 2024', 'A'),
      (6, 8, 'Spring 2024', 'B'),
      (7, 9, 'Fall 2024', 'C'),
      (8, 10, 'Fall 2024', 'B');
      ```

   4. Create `courses` Table

      1. **SQL Query:**

      ```sql
      CREATE TABLE courses (
          course_id INT AUTO_INCREMENT PRIMARY KEY,
          course_name VARCHAR(100) NOT NULL,
          course_description VARCHAR(255),
          marks_subj1 INT,
          marks_subj2 INT,
          marks_subj3 INT
      );
      ```

      2. **Insert Records:**

      ```sql
      INSERT INTO courses (course_name, course_description, marks_subj1, marks_subj2, marks_subj3) VALUES
      ('Mathematics 101', 'Introduction to Mathematics', 65, 70, 75),
      ('Physics 101', 'Fundamentals of Physics', 75, 80, 70),
      ('Chemistry 101', 'Basics of Chemistry', 70, 65, 75),
      ('Biology 101', 'Introduction to Biology', 55, 60, 65),
      ('History 101', 'World History Overview', 65, 70, 60),
      ('Computer Science 101', 'Introduction to Computer Science', 75, 80, 85),
      ('Literature 101', 'Introduction to Literature', 70, 65, 75),
      ('Economics 101', 'Fundamentals of Economics', 55, 60, 65),
      ('Philosophy 101', 'Introduction to Philosophy', 65, 70, 75),
      ('Art 101', 'Introduction to Art and Design', 75, 70, 65);
      ```

1. **Step 3: Create Java Program to Connect to the Database**

   1. Here’s the Java code to connect to the database and retrieve records from the `studentDetail` table:
   2. **JDBCDemo.java**

      ```java
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
      ```

   3. **Output:**

      ```bash
      >>> javac JDBCDemo.java

      >>>java -cp "D:\MCA(2024-26)\Mohammed-Varaliya\Core-Java-For-College\JDBC\mysql-connector-j-8.0.31.jar"; JDBCDemo
      Connected to the database successfully!
      Student Name         Roll No         Phone No        Gender
      -------------------------------------------------------
      Priya Sharma         1               9988776655      Female
      Ravi Kumar           2               9871234567      Male
      Sneha Patel          3               9765432109      Female
      Vikram Singh         4               9654321098      Male
      Aditi Mehta          5               9543210987      Female
      Arjun Reddy          6               9432109876      Male
      Neha Gupta           7               9321098765      Female
      Rahul Joshi          8               9210987654      Male
      Anup Thakur          53              8765432109      Male
      Mohammed Varaliya    54              9876543210      Male
      Connection closed.
      ```
