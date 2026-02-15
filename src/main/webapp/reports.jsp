<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2/14/2026
  Time: 6:50 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Reports</title>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/assets/css/style.css">
</head>
<body>

<div class="dashboard-container">
    <div class="dashboard-card">

        <h2>Reports</h2>

        <!-- TABS -->
        <div class="report-tabs">
            <a href="${pageContext.request.contextPath}/reports/revenue"
               class="report-tab">
                💰 Revenue Report
            </a>

            <a href="${pageContext.request.contextPath}/reports/reservations"
               class="report-tab">
                📋 Reservation Report
            </a>

            <a href="${pageContext.request.contextPath}/reports/occupancy"
               class="report-tab">
                🏨 Room Occupancy Report
            </a>
        </div>

    </div>
</div>

</body>
</html>
