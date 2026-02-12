<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2/12/2026
  Time: 10:40 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<h2>User Management</h2>

<a href="user-add" class="btn-small">+ Add User</a>

<table class="modern-table">
    <thead>
    <tr>
        <th>ID</th>
        <th>Username</th>
        <th>Role</th>
        <th>Actions</th>
    </tr>
    </thead>

    <tbody>
    <c:forEach items="${users}" var="u">
        <tr>
            <td>${u.userId}</td>
            <td>${u.username}</td>
            <td>${u.role}</td>
            <td>
                <a href="user-edit?id=${u.userId}">Edit</a>
                |
                <a href="user-delete?id=${u.userId}">Delete</a>
                |
                <a href="user-reset?id=${u.userId}">Reset Password</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>


</body>
</html>
