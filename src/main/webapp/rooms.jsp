<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2/10/2026
  Time: 10:13 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>Rooms</h2>

<form method="get" class="filter-bar">
    <input type="date" name="checkIn" required>
    <input type="date" name="checkOut" required>
    <button class="btn-small">Check Availability</button>
</form>

<table class="modern-table">
    <thead>
    <tr>
        <th>Room No</th>
        <th>Type</th>
        <th>Price</th>
        <th>Status</th>
        <th>Action</th>
    </tr>
    </thead>

    <tbody>
    <c:forEach items="${rooms}" var="r">
        <tr>
            <td>${r.room.roomNumber}</td>
            <td>${r.room.roomType}</td>
            <td>${r.room.price}</td>
            <td>
                <c:choose>
                    <c:when test="${r.available}">
                        <span class="status CONFIRMED">AVAILABLE</span>
                    </c:when>
                    <c:otherwise>
                        <span class="status CANCELLED">NOT AVAILABLE</span>
                    </c:otherwise>
                </c:choose>
            </td>
            <td>
                <c:if test="${r.available}">
                    <a href="reservation-form?roomId=${r.room.roomId}"
                       class="btn-small">
                        Reserve
                    </a>
                </c:if>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>
