<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2/2/2026
  Time: 9:21 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page session="true" %>
<%
    if (session.getAttribute("username") == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<html>
<head>
    <title>Create Reservation</title>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/assets/css/style.css">
</head>
<body>

<!-- NAVBAR -->
<div class="navbar">
    <div class="logo">Hotel Reservation System</div>

    <div class="nav-right">
        <span class="username">
            <b><%= session.getAttribute("username") %></b>
        </span>

        <form action="${pageContext.request.contextPath}/logout" method="post">
            <button type="submit">Logout</button>
        </form>
    </div>
</div>

<!-- PAGE CONTENT -->
<div class="dashboard-container">
    <div class="dashboard-card">
        <h2>Create Reservation</h2>

        <form action="${pageContext.request.contextPath}/reservation" method="post">

            <div class="input-group">
                <label>Customer Name</label>
                <input type="text" name="customerName" required>
            </div>

            <div class="input-group">
                <label>Room Type</label>
                <select name="roomType" required style="width:100%; padding:10px;">
                    <option value="">-- Select Room --</option>
                    <option value="Single">Single Room</option>
                    <option value="Double">Double Room</option>
                    <option value="Deluxe">Deluxe Room</option>
                    <option value="Suite">Suite</option>
                </select>
            </div>

            <div class="input-group">
                <label>Check-in Date</label>
                <input type="date" name="checkIn" required>
            </div>

            <div class="input-group">
                <label>Check-out Date</label>
                <input type="date" name="checkOut" required>
            </div>

            <button class="btn" type="submit">Save Reservation</button>
        </form>

        <br/>

        <a href="dashboard.jsp" style="text-decoration:none; color:#1e3c72;">
            ← Back to Dashboard
        </a>
    </div>
</div>

</body>
</html>

