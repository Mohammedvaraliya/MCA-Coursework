<%@ page import="org.kjsim.StudentBean, java.util.*, java.sql.*" %>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" type="text/css" href="../../css/styles.css">
  <title>Results Page</title>
</head>
<body>
<%@ include file="../../header.jsp" %>
<div class="container">
<main>
  <h2>Results - Student Records</h2>

  <%
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    List<StudentBean> studentList = new ArrayList<>();

    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/adv-java", "root", "");
      pstmt = conn.prepareStatement("SELECT * FROM student");
      rs = pstmt.executeQuery();

      while (rs.next()) {
        StudentBean student = new StudentBean();
        student.setRollno(rs.getString("rollno"));
        student.setName(rs.getString("name"));
        student.setEmail(rs.getString("email"));
        student.setDepartment(rs.getString("department"));
        studentList.add(student);
      }

      if (studentList.isEmpty()) {
  %>
  <p>No records found.</p>
  <%
  } else {
  %>
  <table>
    <tr>
      <th>Roll No</th>
      <th>Name</th>
      <th>Email</th>
      <th>Department</th>
    </tr>
    <%
      for (StudentBean student : studentList) {
    %>
    <tr>
      <td><%= student.getRollno() %></td>
      <td><%= student.getName() %></td>
      <td><%= student.getEmail() %></td>
      <td><%= student.getDepartment() %></td>
    </tr>
    <%
      }
    %>
  </table>
  <%
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      try {
        if (rs != null) rs.close();
        if (pstmt != null) pstmt.close();
        if (conn != null) conn.close();
      } catch (SQLException se) {
        se.printStackTrace();
      }
    }
  %>
</main>
</div>
<%@ include file="../../footer.jsp" %>

</body>
</html>