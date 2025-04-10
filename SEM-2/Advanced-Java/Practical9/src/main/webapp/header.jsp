<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String currentPath = request.getRequestURI();
    String contextPath = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Employee Management System</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/styles/style.css" rel="stylesheet">

    <style>
        .nav-button-group .btn {
            min-width: 120px;
        }
        .btn.active {
            color: #fff !important;
        }
    </style>
</head>
<body>
<header class="bg-primary-subtle text-primary-emphasis text-center py-4 mb-4">
    <div class="container">
        <h1 class="display-4 fw-bold">Employee Management System</h1>
        <div class="d-flex justify-content-center pt-4 nav-button-group gap-2 flex-wrap">

            <form action="<%= contextPath %>/" method="get">
                <button type="submit"
                        class="btn <%= currentPath.equals(contextPath + "/") ? "btn-primary active" : "btn-outline-primary" %>">
                    Home
                </button>
            </form>

            <form action="<%= contextPath %>/blogs" method="get">
                <button type="submit"
                        class="btn <%= currentPath.startsWith(contextPath + "/blogs") ? "btn-success active" : "btn-outline-success" %>">
                    Blog Posts
                </button>
            </form>

        </div>
    </div>
</header>

<main id="page-content" class="container">
