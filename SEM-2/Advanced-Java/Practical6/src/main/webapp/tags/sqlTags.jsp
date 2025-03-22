<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>

<sql:setDataSource
        var="mydb"
        driver="com.mysql.cj.jdbc.Driver"
        url="jdbc:mysql://localhost:3306/adv-java"
        user="root"
        password=""/>

<sql:query var="students" dataSource="${mydb}">
  SELECT * FROM student;
</sql:query>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>JSP Core Tag (SQL)</title>
  <link rel="stylesheet" href="../style/style.css">
</head>
<body>
<div class="container">
  <h1>Core Tags SQL</h1>

  <div class="buttons">
    <table border="1">
      <thead>
      <tr>
        <th>Roll NO</th>
        <th>Name</th>
        <th>Email</th>
        <th>Department</th>
      </tr>
      </thead>
      <tbody>
      <c:forEach var="student" items="${students.rows}">
        <tr>
          <td><c:out value="${student.rollno}"/></td>
          <td><c:out value="${student.name}"/></td>
          <td><c:out value="${student.email}"/></td>
          <td><c:out value="${student.department}"/></td>
        </tr>
      </c:forEach>
      </tbody>
    </table>
  </div>
</div>
</body>
</html>