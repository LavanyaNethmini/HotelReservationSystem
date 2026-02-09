<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2/9/2026
  Time: 10:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>Guests</h2>

<form method="get" action="guests" class="filter-bar">
    <input type="text" name="search"
           placeholder="Search by name or phone">
    <button class="btn-small">Search</button>
</form>

<table class="modern-table">
    <thead>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Phone</th>
        <th>Email</th>
        <th>Address</th>
        <th>Action</th>
    </tr>
    </thead>

    <tbody>
    <c:forEach items="${guests}" var="g">
        <tr>
            <td>${g.guestId}</td>
            <td>${g.name}</td>
            <td>${g.phone}</td>
            <td>${g.email}</td>
            <td>${g.address}</td>
            <td>
                <a href="guest-edit?id=${g.guestId}">Edit</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>


</body>
</html>
