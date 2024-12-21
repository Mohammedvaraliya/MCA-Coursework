# JDBC Program Execution Guide

This document outlines the steps to compile and run a JDBC program with MySQL using the MySQL Connector/J driver.

### Prerequisites

1. **Java Development Kit (JDK)** installed on your machine.
2. **MySQL Database** setup and running.
3. **MySQL Connector/J Driver** (JDBC Driver for MySQL) located at:
   ```
   D:\Documents\MCA Course\MCA-Coursework\SEM-1\Core-Java\Drivers\mysql-connector-j-9.1.0\mysql-connector-j-9.1.0.jar
   ```
4. **Class-Files** directory where the compiled `.class` files will be stored.

---

### Step 1: Compile the Java Program

Before executing the JDBC program, we need to compile the `.java` file into a `.class` file.

1. Open **Command Prompt** or **Terminal**.
2. Navigate to the directory where your `FileName.java` (the JDBC program file) is located.
   ```bash
   cd <path_to_your_jdbc_program>
   ```
   For example:
   ```bash
   cd D:\Documents\MCA Course\MCA-Coursework\SEM-1\Core-Java\Practical-Session-12(24-oct-2024)
   ```
3. Use the `javac` command to compile your Java program and save the `.class` file in the **Class-Files** directory.

   ```bash
   javac -d "Class-Files" FileName.java
   ```

   Here, replace `FileName.java` with the actual name of the Java program file.

   **Explanation:**

   - `javac` compiles the `.java` source file.
   - The `-d` flag specifies the destination directory for the compiled `.class` file (in this case, **Class-Files**).

4. After running the `javac` command, our compiled `.class` file should be located in the **Class-Files** directory.

---

### Step 2: Execute the Program

To execute the program, we have to use the `java` command, specifying the classpath to both the **Class-Files** directory (where `.class` file is) and the **MySQL JDBC Driver**.

1. **Navigate** to the root directory where your `Class-Files` folder is located.

   ```bash
   cd D:\Documents\MCA Course\MCA-Coursework\SEM-1\Core-Java\Practical-Session-12(24-oct-2024)
   ```

2. **Run the JDBC Program** with the following command:

   ```bash
   java -cp "Class-Files;D:\Documents\MCA Course\MCA-Coursework\SEM-1\Core-Java\Drivers\mysql-connector-j-9.1.0\mysql-connector-j-9.1.0.jar" FileName
   ```

   **Explanation:**

   - **`;`** is the classpath separator used in Windows.
   - **`D:\Documents\MCA Course\MCA-Coursework\SEM-1\Core-Java\Drivers\mysql-connector-j-9.1.0\mysql-connector-j-9.1.0.jar`** is the path to the MySQL JDBC driver.

---
