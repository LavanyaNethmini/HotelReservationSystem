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

    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
</head>

<body>

<div class="dashboard-container">
    <div class="dashboard-card">

        <h2>📊 Reports Dashboard</h2>

        <!-- ================= TABS ================= -->
        <div class="tabs">
            <a class="tab-btn ${type == 'revenue' ? 'active' : ''}"
               href="${pageContext.request.contextPath}/reports?type=revenue">
                💰 Revenue
            </a>

            <a class="tab-btn ${type == 'reservation' ? 'active' : ''}"
               href="${pageContext.request.contextPath}/reports?type=reservation">
                📋 Reservations
            </a>

            <a class="tab-btn ${type == 'occupancy' ? 'active' : ''}"
               href="${pageContext.request.contextPath}/reports?type=occupancy">
                🏨 Occupancy
            </a>
        </div>

        <div class="report-section">

            <!-- ===================================================== -->
            <!-- ================= REVENUE REPORT ==================== -->
            <!-- ===================================================== -->

            <c:if test="${type == 'revenue'}">

                <div class="report-card">

                    <form method="get"
                          action="${pageContext.request.contextPath}/reports"
                          class="report-form">

                        <input type="hidden" name="type" value="revenue">

                        <div class="filter-group">
                            <label>Year</label>
                            <input type="number"
                                   name="year"
                                   placeholder="Enter Year"
                                   value="${param.year}">
                        </div>

                        <div class="filter-group">
                            <label>Start Date</label>
                            <input type="date"
                                   name="start"
                                   value="${param.start}">
                        </div>

                        <div class="filter-group">
                            <label>End Date</label>
                            <input type="date"
                                   name="end"
                                   value="${param.end}">
                        </div>

                        <button type="submit" class="btn-primary">
                            Generate
                        </button>

                    </form>
                </div>

                <!-- Total Revenue -->
                <c:if test="${not empty totalRevenue}">
                    <div class="stat-card">
                        <h4>Total Revenue</h4>
                        <p class="stat-value">
                            LKR ${totalRevenue}
                        </p>
                    </div>
                </c:if>

                <!-- Monthly Chart -->
                <c:if test="${not empty monthlyRevenue}">
                    <div class="chart-card">
                        <canvas id="revenueChart"></canvas>
                    </div>
                </c:if>

            </c:if>


            <!-- ===================================================== -->
            <!-- ================= RESERVATION REPORT ================ -->
            <!-- ===================================================== -->

            <c:if test="${type == 'reservation'}">

                <div class="report-card">
                    <form method="get"
                          action="${pageContext.request.contextPath}/reports"
                          class="report-form">

                        <input type="hidden" name="type" value="reservation">

                        <input type="date" name="start" required>
                        <input type="date" name="end" required>

                        <button type="submit" class="btn-primary">
                            Generate
                        </button>
                    </form>
                </div>

                <c:if test="${not empty reservationReport}">
                    <table class="report-table">
                        <thead>
                        <tr>
                            <th>ID</th>
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

            </c:if>


            <!-- ===================================================== -->
            <!-- ================= OCCUPANCY REPORT ================== -->
            <!-- ===================================================== -->

            <c:if test="${type == 'occupancy'}">

                <div class="report-card">
                    <form method="get"
                          action="${pageContext.request.contextPath}/reports"
                          class="report-form">

                        <input type="hidden" name="type" value="occupancy">

                        <input type="date" name="start" required>
                        <input type="date" name="end" required>

                        <button type="submit" class="btn-primary">
                            Generate
                        </button>
                    </form>
                </div>

                <c:if test="${not empty occupancyReport}">

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
                                <td>${o.occupancyRate}%</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>

                    <div class="chart-card">
                        <canvas id="occupancyChart"></canvas>
                    </div>

                </c:if>

            </c:if>

        </div>
    </div>
</div>


<!-- ================= REVENUE CHART SCRIPT ================= -->
<c:if test="${type == 'revenue' && not empty monthlyRevenue}">
    <script>

        const months = [
            <c:forEach items="${monthlyRevenue}" var="m">
            "Month ${m.month}",
            </c:forEach>
        ];

        const revenueData = [
            <c:forEach items="${monthlyRevenue}" var="m">
            ${m.totalRevenue},
            </c:forEach>
        ];

        new Chart(document.getElementById('revenueChart'), {
            type: 'bar',
            data: {
                labels: months,
                datasets: [{
                    label: 'Monthly Revenue',
                    data: revenueData,
                    backgroundColor: '#2f4f88'
                }]
            },
            options: {
                responsive: true
            }
        });

    </script>
</c:if>


<!-- ================= OCCUPANCY CHART SCRIPT ================= -->
<c:if test="${type == 'occupancy' && not empty occupancyReport}">
    <script>

        const rooms = [
            <c:forEach items="${occupancyReport}" var="o">
            "Room ${o.roomNumber}",
            </c:forEach>
        ];

        const occupancy = [
            <c:forEach items="${occupancyReport}" var="o">
            ${o.occupancyRate},
            </c:forEach>
        ];

        new Chart(document.getElementById('occupancyChart'), {
            type: 'line',
            data: {
                labels: rooms,
                datasets: [{
                    label: 'Occupancy %',
                    data: occupancy,
                    borderColor: '#1e3c72',
                    tension: 0.4
                }]
            },
            options: {
                responsive: true
            }
        });

    </script>
</c:if>

</body>
</html>
