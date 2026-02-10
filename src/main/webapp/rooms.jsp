<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2/10/2026
  Time: 10:13 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Rooms</title>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/assets/css/style.css">
</head>

<body>

<!-- ===== NAVBAR (reuse existing one if included globally) ===== -->

<div class="dashboard-container">
    <div class="dashboard-card">

        <h2>Rooms</h2>

        <!-- ===== FILTER BAR ===== -->
        <form method="get"
              action="${pageContext.request.contextPath}/rooms"
              class="filter-bar">

            <input type="date" name="checkIn" required>
            <input type="date" name="checkOut" required>

            <button type="submit" class="btn-small">
                Check Availability
            </button>
        </form>

        <!-- ===== TABLE ===== -->
        <div class="table-wrapper">
            <table class="modern-table">
                <thead>
                <tr>
                    <th>Room No</th>
                    <th>Type</th>
                    <th>Price (LKR)</th>
                    <th>Status</th>
                    <th>Action</th>
                </tr>
                </thead>

                <tbody>

                <c:if test="${empty rooms}">
                    <tr>
                        <td colspan="5" class="no-data">
                            No rooms found for selected dates
                        </td>
                    </tr>
                </c:if>

                <c:forEach items="${rooms}" var="room">
                    <tr>
                        <td>${room.roomNumber}</td>
                        <td>${room.roomType}</td>
                        <td>${room.price}</td>

                        <td>
                            <c:choose>
                                <c:when test="${room.available}">
                                    <span class="status AVAILABLE">AVAILABLE</span>
                                </c:when>
                                <c:otherwise>
                                    <span class="status NOT_AVAILABLE">NOT AVAILABLE</span>
                                </c:otherwise>
                            </c:choose>
                        </td>

                        <td>
                            <c:choose>
                                <c:when test="${room.available}">
                                    <a href="reserve?roomId=${room.roomId}"
                                       class="btn-small">
                                        Reserve
                                    </a>
                                </c:when>
                                <c:otherwise>
                                    <button class="btn-disabled" disabled>
                                        Unavailable
                                    </button>
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                </c:forEach>

                </tbody>
            </table>
        </div>

    </div>
</div>

</body>

</html>
