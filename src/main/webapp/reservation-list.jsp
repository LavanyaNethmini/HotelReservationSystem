<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
  <title>Reservations</title>
</head>
<body>

<h2>Reservations</h2>

<form method="get" action="reservation">
  <input type="text" name="search"
         placeholder="Search by name or phone">

  <input type="month" name="month">

  <button type="submit">Filter</button>
</form>

<br>

<table border="1" width="100%" cellpadding="8">
  <tr>
  <tr>
    <th>ID</th>
    <th>Guest Name</th>
    <th>Phone</th>
    <th>Room</th>
    <th>Check In</th>
    <th>Check Out</th>
    <th>Status</th>
    <th>Actions</th>
  </tr>

  </tr>

  <c:choose>
    <c:when test="${empty reservations}">
      <tr>
        <td colspan="6" align="center">
          No reservations found
        </td>
      </tr>
    </c:when>

    <c:otherwise>
      <c:forEach items="${reservations}" var="r">
        <tr>
          <td>${r.reservationId}</td>
          <td>${r.guestName}</td>
          <td>${r.guestPhone}</td>
          <td>${r.roomId}</td>
          <td>${r.checkIn}</td>
          <td>${r.checkOut}</td>
          <td>${r.status}</td>
          <td>
            <a href="cancel-reservation?id=${r.reservationId}">
              Cancel
            </a>
          </td>
        </tr>
      </c:forEach>
    </c:otherwise>
  </c:choose>
</table>

</body>
</html>
