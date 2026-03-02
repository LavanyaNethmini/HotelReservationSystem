<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2/14/2026
  Time: 8:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2/14/2026
  Time: 8:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page session="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
    <title>Reports | Hotel Reservation</title>

    <script src="https://cdn.tailwindcss.com"></script>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
</head>

<body class="bg-gray-100 font-sans">

<div class="flex h-screen">

    <jsp:include page="includes/sidebar.jsp" />

        <!-- ===== PAGE CONTENT ===== -->
        <main class="flex-1 overflow-y-auto p-10">

            <!-- Page Header -->
            <div class="mb-8">
                <h1 class="text-3xl font-bold text-gray-800">
                    Reports & Analytics
                </h1>
                <p class="text-gray-500 mt-2">
                    Analyze revenue, reservations, and occupancy statistics.
                </p>
            </div>

            <!-- TABS -->
            <div class="flex gap-4 mb-8">

                <a href="${pageContext.request.contextPath}/reports?type=revenue"
                   class="px-6 py-3 rounded-xl shadow
                   ${type == 'revenue' ? 'bg-indigo-600 text-white' : 'bg-white'}">
                    💰 Revenue
                </a>

                <a href="${pageContext.request.contextPath}/reports?type=reservation"
                   class="px-6 py-3 rounded-xl shadow
                   ${type == 'reservation' ? 'bg-indigo-600 text-white' : 'bg-white'}">
                    📋 Reservations
                </a>

                <a href="${pageContext.request.contextPath}/reports?type=occupancy"
                   class="px-6 py-3 rounded-xl shadow
                   ${type == 'occupancy' ? 'bg-indigo-600 text-white' : 'bg-white'}">
                    🏨 Occupancy
                </a>

            </div>

            <!-- ================= REVENUE ================= -->
            <c:if test="${type == 'revenue'}">

                <div class="bg-white p-8 rounded-3xl shadow-xl mb-8">

                    <form method="get"
                          action="${pageContext.request.contextPath}/reports"
                          class="grid md:grid-cols-3 gap-6">

                        <input type="hidden" name="type" value="revenue"/>



                        <input type="date" name="start"
                               class="border px-4 py-3 rounded-xl"/>

                        <input type="date" name="end"
                               class="border px-4 py-3 rounded-xl"/>

                        <button type="submit"
                                class="bg-indigo-600 text-white rounded-xl hover:bg-indigo-700 transition">
                            Generate
                        </button>
                    </form>
                </div>

                <c:if test="${not empty totalRevenue}">
                    <div class="bg-gradient-to-r from-emerald-400 to-green-600
                                text-white p-8 rounded-3xl shadow-xl mb-8">
                        <h4 class="text-sm opacity-80">Total Revenue</h4>
                        <p class="text-4xl font-bold mt-3">
                            LKR <fmt:formatNumber value="${totalRevenue}" type="number" minFractionDigits="2" />
                        </p>
                    </div>
                </c:if>

                <c:choose>

                    <c:when test="${not empty billList}">

                        <div class="bg-white p-8 rounded-3xl shadow-xl overflow-x-auto">

                            <h3 class="text-lg font-semibold text-indigo-600 mb-4">
                                Revenue Report
                            </h3>

                            <div class="mb-6 text-right font-semibold text-gray-700">
                                Total Revenue: LKR <fmt:formatNumber value="${totalRevenue}" type="number" minFractionDigits="2" />
                            </div>

                            <table class="min-w-full text-center border-collapse">

                                <thead class="bg-indigo-600 text-white">
                                <tr>
                                    <th class="px-6 py-3">Reservation ID</th>
                                    <th class="px-6 py-3">Nights</th>
                                    <th class="px-6 py-3">Room Rate</th>
                                    <th class="px-6 py-3">Payment</th>
                                    <th class="px-6 py-3">Total Amount</th>
                                </tr>
                                </thead>

                                <tbody>

                                <c:forEach items="${billList}" var="b">
                                    <tr class="border-b hover:bg-gray-50">
                                        <td class="px-6 py-4">${b.reservationId}</td>
                                        <td class="px-6 py-4">${b.nights}</td>
                                        <td class="px-6 py-4"><fmt:formatNumber value="${b.roomRate}" type="number" minFractionDigits="2" /></td>
                                        <td class="px-6 py-4">${b.paymentMethod}</td>
                                        <td class="px-6 py-4 font-semibold">
                                            <fmt:formatNumber value="${b.roomRate}" type="number" minFractionDigits="2" />
                                        </td>
                                    </tr>
                                </c:forEach>

                                </tbody>

                            </table>

                        </div>

                    </c:when>

                    <c:otherwise>

                        <div class="bg-white p-10 rounded-3xl shadow-xl text-center">
                            <div class="text-5xl mb-4">💰</div>
                            <h3 class="text-lg font-semibold text-gray-700">
                                No Revenue Data Found
                            </h3>
                        </div>

                    </c:otherwise>



                </c:choose>

                <c:if test="${not empty monthlyRevenue}">
                    <div class="bg-white p-8 rounded-3xl shadow-xl">
                        <canvas id="revenueChart"></canvas>
                    </div>
                </c:if>

            </c:if>







            <c:if test="${not empty monthlyRevenue}">

                <div class="bg-white p-8 rounded-3xl shadow-xl mt-8">

                    <h3 class="text-lg font-semibold text-indigo-600 mb-6">
                        Monthly Revenue Breakdown
                    </h3>

                    <table class="min-w-full text-left">
                        <thead class="bg-indigo-600 text-white">
                        <tr>
                            <th class="px-4 py-3">Month</th>
                            <th class="px-4 py-3">Revenue (LKR)</th>
                        </tr>
                        </thead>

                        <tbody>
                        <c:forEach items="${monthlyRevenue}" var="m">
                            <tr class="border-b hover:bg-gray-50">
                                <td class="px-4 py-3">Month ${m.month}</td>
                                <td class="px-4 py-3 font-semibold">
                                        ${m.totalRevenue}
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>

                </div>

            </c:if>


            <!-- ================= RESERVATIONS ================= -->
            <c:if test="${type == 'reservation'}">

                <!-- Filter Form -->
                <div class="bg-white p-8 rounded-3xl shadow-xl mb-8">
                    <form method="get"
                          action="${pageContext.request.contextPath}/reports"
                          class="grid md:grid-cols-3 gap-6">

                        <input type="hidden" name="type" value="reservation"/>

                        <input type="date" name="start"
                               class="border px-4 py-3 rounded-xl"/>

                        <input type="date" name="end"
                               class="border px-4 py-3 rounded-xl"/>

                        <button type="submit"
                                class="bg-indigo-600 text-white rounded-xl hover:bg-indigo-700 transition">
                            Generate
                        </button>
                    </form>
                </div>


                <c:choose>


                    <c:when test="${not empty reservationReport}">

                        <div class="bg-white p-8 rounded-3xl shadow-xl overflow-x-auto mb-8">

                            <div class="flex justify-between items-center mb-6">
                                <h3 class="text-lg font-semibold text-indigo-600">
                                    Reservation Report
                                </h3>

                                <div class="text-sm text-gray-500">
                                    Total Reservations:
                                    <span class="font-semibold text-gray-700">
                                            ${totalReservations}
                                    </span>
                                </div>
                            </div>

                            <table class="min-w-full text-center border-collapse">

                                <thead class="bg-indigo-600 text-white">
                                <tr>
                                    <th class="px-6 py-3">Reservation ID</th>
                                    <th class="px-6 py-3">Guest Name</th>
                                    <th class="px-6 py-3">Room No</th>
                                    <th class="px-6 py-3">Status</th>
                                </tr>
                                </thead>

                                <tbody class="text-gray-700">

                                <c:forEach items="${reservationReport}" var="r">
                                    <tr class="border-b hover:bg-gray-50 transition">
                                        <td class="px-6 py-4 font-semibold">
                                                ${r.reservationId}
                                        </td>
                                        <td class="px-6 py-4">
                                                ${r.guestName}
                                        </td>
                                        <td class="px-6 py-4">
                                                ${r.roomNumber}
                                        </td>
                                        <td class="px-6 py-4">
                                                ${r.status}
                                        </td>
                                    </tr>
                                </c:forEach>

                                </tbody>

                            </table>

                        </div>

                    </c:when>


                    <c:otherwise>

                        <div class="bg-white p-10 rounded-3xl shadow-xl text-center mb-8">

                            <div class="text-5xl mb-4">📭</div>

                            <h3 class="text-lg font-semibold text-gray-700 mb-2">
                                No Reservations Found
                            </h3>

                            <p class="text-gray-500">
                                There are no reservations within the selected date range.
                            </p>

                        </div>

                    </c:otherwise>

                </c:choose>

            </c:if>

            <!-- ================= OCCUPANCY ================= -->
            <c:if test="${type == 'occupancy'}">

                <div class="bg-white p-8 rounded-3xl shadow-xl mb-8">

                    <form method="get"
                          action="${pageContext.request.contextPath}/reports"
                          class="grid md:grid-cols-3 gap-6">

                        <input type="hidden" name="type" value="occupancy"/>

                        <input type="date" name="start"
                               class="border px-4 py-3 rounded-xl"/>

                        <input type="date" name="end"
                               class="border px-4 py-3 rounded-xl"/>

                        <button type="submit"
                                class="bg-indigo-600 text-white rounded-xl hover:bg-indigo-700 transition">
                            Generate
                        </button>
                    </form>
                </div>

                <c:if test="${not empty occupancyReport}">
                    <div class="bg-white p-8 rounded-3xl shadow-xl overflow-x-auto mb-8">

                        <h3 class="text-lg font-semibold text-indigo-600 mb-6">
                            Room Occupancy Report
                        </h3>

                        <table class="min-w-full text-center border-collapse">

                            <thead class="bg-indigo-600 text-white">
                            <tr>
                                <th class="px-6 py-3">Room No</th>
                                <th class="px-6 py-3">Type</th>
                                <th class="px-6 py-3">Total Days</th>
                                <th class="px-6 py-3">Booked Days</th>
                                <th class="px-6 py-3">Occupancy %</th>
                            </tr>
                            </thead>

                            <tbody class="text-gray-700">

                            <c:forEach items="${occupancyReport}" var="o">
                                <tr class="border-b hover:bg-gray-50 transition">

                                    <td class="px-6 py-4 font-semibold">
                                            ${o.roomNumber}
                                    </td>

                                    <td class="px-6 py-4">
                                            ${o.roomType}
                                    </td>

                                    <td class="px-6 py-4">
                                            ${o.totalDays}
                                    </td>

                                    <td class="px-6 py-4">
                                            ${o.bookedDays}
                                    </td>

                                    <td class="px-6 py-4">

                                        <c:choose>

                                            <c:when test="${o.occupancyRate >= 80}">
                                <span class="bg-red-500 text-white px-3 py-1 rounded-full text-sm font-semibold">
                                    ${o.occupancyRate}%
                                </span>
                                            </c:when>

                                            <c:when test="${o.occupancyRate >= 50}">
                                <span class="bg-yellow-500 text-white px-3 py-1 rounded-full text-sm font-semibold">
                                    ${o.occupancyRate}%
                                </span>
                                            </c:when>

                                            <c:otherwise>
                                <span class="bg-green-500 text-white px-3 py-1 rounded-full text-sm font-semibold">
                                    ${o.occupancyRate}%
                                </span>
                                            </c:otherwise>

                                        </c:choose>

                                    </td>

                                </tr>
                            </c:forEach>

                            </tbody>

                        </table>

                    </div>
                </c:if>
            </c:if>

        </main>
    </div>
</div>




is this correct



</body>
</html>