<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2/14/2026
  Time: 8:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Reports Dashboard</title>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/assets/css/style.css">

    <style>

    </style>
</head>

<body>

<div class="dashboard-container">
    <div class="dashboard-card">

        <h2>Reports Dashboard</h2>

        <!-- ===== TABS ===== -->
        <div class="tabs">
            <a class="tab-btn ${type == 'revenue' ? 'active' : ''}"
               href="${pageContext.request.contextPath}/reports?type=revenue">
                Revenue Report
            </a>

            <a class="tab-btn ${type == 'reservation' ? 'active' : ''}"
               href="${pageContext.request.contextPath}/reports?type=reservation">
                Reservation Report
            </a>

            <a class="tab-btn ${type == 'occupancy' ? 'active' : ''}"
               href="${pageContext.request.contextPath}/reports?type=occupancy">
                Room Occupancy
            </a>
        </div>

        <div class="report-section">

            <!-- ================= REVENUE REPORT ================= -->
            <c:if test="${type == 'revenue'}">

                <form class="report-form" method="get"
                      action="${pageContext.request.contextPath}/reports">

                    <input type="hidden" name="type" value="revenue">

                    <input type="date" name="start" required>
                    <input type="date" name="end" required>

                    <button type="submit">Generate</button>
                </form>

                <c:if test="${not empty totalRevenue}">
                    <h3>Total Revenue: 💰 ${totalRevenue}</h3>
                </c:if>

            </c:if>


            <!-- ================= RESERVATION REPORT ================= -->
            <c:if test="${type == 'reservation'}">

                <form class="report-form" method="get"
                      action="${pageContext.request.contextPath}/reports">

                    <input type="hidden" name="type" value="reservation">

                    <input type="date" name="start" required>
                    <input type="date" name="end" required>

                    <button type="submit">Generate</button>
                </form>

                <table class="report-table">
                    <thead>
                    <tr>
                        <th>Reservation ID</th>
                        <th>Guest</th>
                        <th>Room</th>
                        <th>Status</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${reservationReport}" var="r">
                        <tr>
                            <td>${r.reservationId}</td>
                            <td>${r.guestName}</td>
                            <td>${r.roomNumber}</td>
                            <td>${r.status}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>

            </c:if>


            <!-- ================= OCCUPANCY REPORT ================= -->
            <c:if test="${type == 'occupancy'}">

                <form class="report-form" method="get"
                      action="${pageContext.request.contextPath}/reports">

                    <input type="hidden" name="type" value="occupancy">

                    <input type="date" name="start" required>
                    <input type="date" name="end" required>

                    <button type="submit">Generate</button>
                </form>

                <table class="report-table">
                    <thead>
                    <tr>
                        <th>Room</th>
                        <th>Type</th>
                        <th>Total Days</th>
                        <th>Booked Days</th>
                        <th>Occupancy %</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${occupancyReport}" var="o">
                        <tr>
                            <td>${o.roomNumber}</td>
                            <td>${o.roomType}</td>
                            <td>${o.totalDays}</td>
                            <td>${o.bookedDays}</td>
                            <td>
                                    ${o.occupancyRate} %
                                <div class="rate-bar"
                                     style="width:${o.occupancyRate}%"></div>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>

            </c:if>

        </div>

    </div>
</div>

</body>
</html>
