<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2/1/2026
  Time: 2:55 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page session="true" %>
<!DOCTYPE html>
<html>
<head>
    <title>Dashboard | Hotel Reservation</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/assets/css/style.css">
</head>

<body>

<!-- ===== NAVBAR ===== -->
<div class="navbar">
    <div class="logo">Hotel Reservation System</div>

    <div class="nav-right">
        <span class="username">
            Welcome,
            <b><%= session.getAttribute("username") %></b>
            (<%= session.getAttribute("role") %>)
        </span>

        <form action="${pageContext.request.contextPath}/logout" method="post">
            <button type="submit">Logout</button>
        </form>
    </div>
</div>

<!-- ===== DASHBOARD CONTENT ===== -->
<div class="dashboard-container">
    <div class="dashboard-card">

        <h2>Dashboard</h2>

        <div class="menu">

            <!-- ===== COMMON (ADMIN + STAFF) ===== -->
            <a href="${pageContext.request.contextPath}/reservation.jsp"
               class="menu-item"
               style="text-decoration:none; color:inherit;">
                Make Reservation
            </a>

            <a href="${pageContext.request.contextPath}/reservation"
               class="menu-item"
               style="text-decoration:none; color:inherit;">
                Reservation List
            </a>

            <div class="menu-item">
                Customers
            </div>

            <div class="menu-item">
                Rooms
            </div>

            <a href="${pageContext.request.contextPath}/help.jsp"
               class="menu-item"
               style="text-decoration:none; color:inherit;">
                Help Guide
            </a>

            <!-- ===== ADMIN ONLY ===== -->
            <% if ("ADMIN".equals(session.getAttribute("role"))) { %>

            <div class="menu-item">
                User Management
            </div>

            <div class="menu-item">
                Reports
            </div>

            <div class="menu-item">
                System Settings
            </div>

            <% } %>

        </div>


    </div>

    </div>
</div>

</body>
</html>
