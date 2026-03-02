<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2/1/2026
  Time: 2:55 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.hotel.reservation.service.DashboardService" %>
<%@ page import="com.hotel.reservation.service.impl.DashboardServiceImpl" %>
<%@ page import="java.sql.SQLException" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
    int totalReservations = 0;
    int totalGuests = 0;
    int availableRooms = 0;

    try {
        DashboardService dashboardService = new DashboardServiceImpl();
        totalReservations = dashboardService.getTodayReservations();
        totalGuests = dashboardService.getTotalGuests();
        availableRooms = dashboardService.getAvailableRoomsToday();
    } catch (SQLException e) {
        e.printStackTrace();
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Dashboard | Hotel Reservation</title>

    <script src="https://cdn.tailwindcss.com"></script>
    <script src="https://unpkg.com/feather-icons"></script>
    <script src="https://unpkg.com/heroicons@2.0.18/dist/heroicons.min.js"></script>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/assets/css/style.css">
</head>

<body class="font-sans bg-gray-100">

<!-- ===== MAIN LAYOUT ===== -->
<div class="flex h-screen">

    <jsp:include page="includes/sidebar.jsp" />

        <main class="p-10 max-w-7xl mx-auto">

            <!-- Welcome Section -->
            <div class="mb-12">
                <h1 class="text-3xl font-bold text-gray-800">
                    Welcome back, <%= session.getAttribute("username") %>
                </h1>
                <p class="text-gray-500 mt-3 text-lg">
                    Here's what's happening in your hotel today.
                </p>
            </div>
            <!-- Statistics -->
            <div class="grid grid-cols-1 md:grid-cols-3 gap-8 mb-12">

                <!-- Reservations -->
                <div class="bg-gradient-to-r from-blue-500 to-indigo-600
                p-8 rounded-3xl shadow-2xl
                transform hover:scale-105 transition duration-300 text-white">

                    <div class="flex justify-between items-center">
                        <div>
                            <h4 class="text-sm opacity-90">Total Reservations</h4>
                            <p class="text-4xl font-bold mt-3"><%= totalReservations %></p>
                        </div>
                        <div class="bg-white/20 p-4 rounded-2xl">
                            <svg xmlns="http://www.w3.org/2000/svg"
                                 fill="none" viewBox="0 0 24 24"
                                 stroke-width="1.5" stroke="currentColor"
                                 class="w-6 h-6 text-white">
                                <path stroke-linecap="round" stroke-linejoin="round"
                                      d="M6.75 3v2.25M17.25 3v2.25M3 8.25h18M4.5
              6h15A1.5 1.5 0 0121 7.5v10.5A1.5
              1.5 0 0119.5 19.5h-15A1.5 1.5
              0 013 18V7.5A1.5 1.5 0 014.5 6z" />
                            </svg>
                        </div>
                    </div>
                </div>

                <!-- Guests -->
                <div class="bg-gradient-to-r from-emerald-400 to-green-600
                p-8 rounded-3xl shadow-2xl
                transform hover:scale-105 transition duration-300 text-white">

                    <div class="flex justify-between items-center">
                        <div>
                            <h4 class="text-sm opacity-90">Active Guests</h4>
                            <p class="text-4xl font-bold mt-3"><%= totalGuests %></p>
                        </div>
                        <div class="bg-white/20 p-4 rounded-2xl">
                            <svg xmlns="http://www.w3.org/2000/svg" fill="none"
                                 viewBox="0 0 24 24" stroke-width="1.5"
                                 stroke="currentColor" class="w-6 h-6 text-white">
                                <path stroke-linecap="round" stroke-linejoin="round"
                                      d="M17.982 18.725A7.488 7.488 0 0012
15.75a7.488 7.488 0 00-5.982
2.975m11.964 0a9 9 0 10-11.964
0m11.964 0A8.966 8.966 0
0112 21a8.966 8.966 0
01-5.982-2.275"/>
                            </svg>
                        </div>
                    </div>
                </div>

                <!-- Rooms -->
                <div class="bg-gradient-to-r from-orange-400 to-red-500
                p-8 rounded-3xl shadow-2xl
                transform hover:scale-105 transition duration-300 text-white">

                    <div class="flex justify-between items-center">
                        <div>
                            <h4 class="text-sm opacity-90">Available Rooms</h4>
                            <p class="text-4xl font-bold mt-3"><%= availableRooms %></p>
                        </div>
                        <div class="bg-white/20 p-4 rounded-2xl">
                            <svg xmlns="http://www.w3.org/2000/svg" fill="none"
                                 viewBox="0 0 24 24" stroke-width="1.5"
                                 stroke="currentColor" class="w-6 h-6 text-white">
                                <path stroke-linecap="round" stroke-linejoin="round"
                                      d="M3 21h18M4.5 21V7.5A1.5
1.5 0 016 6h12a1.5 1.5
0 011.5 1.5V21M9 9h.008v.008H9V9zm0
3h.008v.008H9V12zm0 3h.008v.008H9V15zm3-6h.008v.008H12V9zm0
3h.008v.008H12V12zm0
3h.008v.008H12V15z"/>
                            </svg>
                        </div>
                    </div>
                </div>
            </div>

            <!-- QUICK ACTIONS -->
            <div class="bg-white rounded-3xl shadow-xl p-8 mb-12">

                <h3 class="text-xl font-bold text-gray-800 mb-8">
                    Quick Actions
                </h3>



                <div class="grid grid-cols-1 md:grid-cols-4 gap-6">

                    <!-- New Reservation -->
                    <a href="${pageContext.request.contextPath}/reservation.jsp"
                       class="group bg-indigo-50 hover:bg-indigo-100 p-6 rounded-2xl transition shadow-sm">

                        <div class="flex items-center gap-4">
                            <div class="bg-indigo-500 text-white p-3 rounded-xl">
                                <svg xmlns="http://www.w3.org/2000/svg"
                                     fill="none" viewBox="0 0 24 24"
                                     stroke-width="1.5" stroke="currentColor"
                                     class="w-6 h-6">
                                    <path stroke-linecap="round" stroke-linejoin="round"
                                          d="M12 4.5v15m7.5-7.5h-15"/>
                                </svg>
                            </div>
                            <div>
                                <p class="font-semibold text-gray-700">
                                    New Reservation
                                </p>
                            </div>
                        </div>

                    </a>

                    <!-- Manage Guests -->
                    <a href="${pageContext.request.contextPath}/guests"
                       class="group bg-green-50 hover:bg-green-100 p-6 rounded-2xl transition shadow-sm">

                        <div class="flex items-center gap-4">
                            <div class="bg-green-500 text-white p-3 rounded-xl">
                                <svg xmlns="http://www.w3.org/2000/svg"
                                     fill="none" viewBox="0 0 24 24"
                                     stroke-width="1.5" stroke="currentColor"
                                     class="w-6 h-6">
                                    <path stroke-linecap="round" stroke-linejoin="round"
                                          d="M15.75 6.75a3.75 3.75 0 11-7.5 0
                                 3.75 3.75 0 017.5 0zM4.5
                                 20.25a8.25 8.25 0
                                 0115 0"/>
                                </svg>
                            </div>
                            <p class="font-semibold text-gray-700">
                                Manage Guests
                            </p>
                        </div>

                    </a>

                    <!-- Manage Rooms -->
                    <a href="${pageContext.request.contextPath}/rooms"
                       class="group bg-orange-50 hover:bg-orange-100 p-6 rounded-2xl transition shadow-sm">

                        <div class="flex items-center gap-4">
                            <div class="bg-orange-500 text-white p-3 rounded-xl">
                                <svg xmlns="http://www.w3.org/2000/svg"
                                     fill="none" viewBox="0 0 24 24"
                                     stroke-width="1.5" stroke="currentColor"
                                     class="w-6 h-6">
                                    <path stroke-linecap="round" stroke-linejoin="round"
                                          d="M3 21h18M4.5 21V7.5A1.5
                                 1.5 0 016 6h12a1.5 1.5
                                 0 011.5 1.5V21"/>
                                </svg>
                            </div>
                            <p class="font-semibold text-gray-700">
                                Manage Rooms
                            </p>
                        </div>

                    </a>

                    <!-- Help-->
                    <a href="${pageContext.request.contextPath}/help"
                       class="group bg-purple-50 hover:bg-purple-100 p-6 rounded-2xl transition shadow-sm">

                        <div class="flex items-center gap-4">
                            <div class="bg-purple-500 text-white p-3 rounded-xl">
                                <svg xmlns="http://www.w3.org/2000/svg"
                                     fill="none" viewBox="0 0 24 24"
                                     stroke-width="1.5" stroke="currentColor"
                                     class="w-6 h-6">
                                    <path stroke-linecap="round" stroke-linejoin="round"
                                          d="M12 18h.01M9.09 9a3 3 0 115.82 1c0 2-3 2-3 4m0 4h.01"/>
                                </svg>
                            </div>
                            <p class="font-semibold text-gray-700">
                                Help
                            </p>
                        </div>

                    </a>



                </div>

            </div>

            <div class="grid grid-cols-1 md:grid-cols-2 gap-8 mb-12">

                <!-- System Info -->
                <div class="bg-white rounded-3xl shadow-xl p-8">
                    <h3 class="text-lg font-bold text-gray-800 mb-6">
                        System Information
                    </h3>

                    <ul class="space-y-4 text-gray-600">

                        <li class="flex items-center gap-3">
                            <svg xmlns="http://www.w3.org/2000/svg"
                                 fill="none" viewBox="0 0 24 24"
                                 stroke-width="1.5" stroke="currentColor"
                                 class="w-5 h-5 text-indigo-500">
                                <path stroke-linecap="round" stroke-linejoin="round"
                                      d="M6.75 3v2.25M17.25 3v2.25M3
                             8.25h18"/>
                            </svg>
                            Today: <%= java.time.LocalDate.now() %>
                        </li>

                        <li class="flex items-center gap-3">
                            <svg xmlns="http://www.w3.org/2000/svg"
                                 fill="none" viewBox="0 0 24 24"
                                 stroke-width="1.5" stroke="currentColor"
                                 class="w-5 h-5 text-green-500">
                                <path stroke-linecap="round" stroke-linejoin="round"
                                      d="M15.75 6.75a3.75 3.75 0
                             11-7.5 0 3.75 3.75 0
                             017.5 0z"/>
                            </svg>
                            Logged in as: <%= session.getAttribute("role") %>
                        </li>

                    </ul>
                </div>

                <!-- Tips -->
                <div class="bg-white rounded-3xl shadow-xl p-8">
                    <h3 class="text-lg font-bold text-gray-800 mb-6">
                        Quick Tips
                    </h3>

                    <ul class="space-y-3 text-gray-600 text-sm">
                        <li>Monitor daily reservations for occupancy trends.</li>
                        <li>Review reports weekly for revenue growth.</li>
                        <li>Keep user roles properly assigned.</li>
                    </ul>
                </div>
            </div>

            <footer class="mt-16 border-t pt-6 text-center text-gray-400 text-sm">
                © <%= java.time.Year.now() %> Hotel Reservation System |
                Version 1.0 |
                All Rights Reserved
            </footer>

        </main>



    </div>



<script>
    feather.replace()
</script>

</body>
</html>
