<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2/14/2026
  Time: 6:18 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page session="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Revenue Report | Hotel Reservation</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>

<body class="bg-gray-100 font-sans">

<div class="flex h-screen">

    <jsp:include page="includes/sidebar.jsp" />

        <!-- ===== PAGE CONTENT ===== -->
        <main class="flex-1 overflow-y-auto p-10">

            <div class="max-w-6xl mx-auto">

                <!-- Page Header -->
                <div class="mb-10">
                    <h1 class="text-3xl font-bold text-gray-800">
                        Revenue Analytics
                    </h1>
                    <p class="text-gray-500 mt-2">
                        Generate revenue reports by date range or yearly breakdown.
                    </p>
                </div>

                <!-- ================= DATE RANGE REPORT ================= -->
                <div class="bg-white p-8 rounded-3xl shadow-xl mb-8">

                    <h3 class="text-lg font-semibold text-indigo-600 mb-6">
                        Revenue by Date Range
                    </h3>

                    <form method="get"
                          action="${pageContext.request.contextPath}/reports/revenue"
                          class="grid md:grid-cols-3 gap-6">

                        <div>
                            <label class="block text-sm text-gray-600 mb-2">
                                Start Date
                            </label>
                            <input type="date"
                                   name="start"
                                   class="w-full border px-4 py-3 rounded-xl">
                        </div>

                        <div>
                            <label class="block text-sm text-gray-600 mb-2">
                                End Date
                            </label>
                            <input type="date"
                                   name="end"
                                   class="w-full border px-4 py-3 rounded-xl">
                        </div>

                        <div class="flex items-end">
                            <button class="w-full bg-indigo-600 text-white py-3 rounded-xl hover:bg-indigo-700 transition">
                                Calculate
                            </button>
                        </div>
                    </form>

                    <c:if test="${not empty totalRevenue}">
                        <div class="mt-8 bg-gradient-to-r from-emerald-400 to-green-600
                                    text-white p-6 rounded-2xl shadow-lg">
                            <h4 class="text-sm opacity-80">Total Revenue</h4>
                            <p class="text-3xl font-bold mt-2">
                                LKR ${totalRevenue}
                            </p>
                        </div>
                    </c:if>

                </div>

                <!-- ================= YEARLY REPORT ================= -->
                <div class="bg-white p-8 rounded-3xl shadow-xl">

                    <h3 class="text-lg font-semibold text-indigo-600 mb-6">
                        Monthly Revenue by Year
                    </h3>

                    <form method="get"
                          action="${pageContext.request.contextPath}/reports/revenue"
                          class="grid md:grid-cols-2 gap-6 mb-8">

                        <div>
                            <label class="block text-sm text-gray-600 mb-2">
                                Year
                            </label>
                            <input type="number"
                                   name="year"
                                   placeholder="Enter Year"
                                   class="w-full border px-4 py-3 rounded-xl">
                        </div>

                        <div class="flex items-end">
                            <button class="w-full bg-indigo-600 text-white py-3 rounded-xl hover:bg-indigo-700 transition">
                                Generate Monthly Report
                            </button>
                        </div>
                    </form>

                    <c:if test="${not empty monthlyRevenue}">
                        <div class="overflow-x-auto">
                            <table class="min-w-full text-left border rounded-xl overflow-hidden">

                                <thead class="bg-indigo-600 text-white">
                                <tr>
                                    <th class="px-4 py-3">Month</th>
                                    <th class="px-4 py-3">Revenue (LKR)</th>
                                </tr>
                                </thead>

                                <tbody>
                                <c:forEach items="${monthlyRevenue}" var="r">
                                    <tr class="border-b hover:bg-gray-50">
                                        <td class="px-4 py-3">
                                            Month ${r.month}
                                        </td>
                                        <td class="px-4 py-3 font-semibold">
                                                ${r.totalRevenue}
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>

                            </table>
                        </div>
                    </c:if>

                </div>

            </div>

        </main>

    </div>
</div>

</body>
</html>