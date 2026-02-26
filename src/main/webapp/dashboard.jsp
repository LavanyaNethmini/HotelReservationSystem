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

    <!-- ===== SIDEBAR ===== -->
    <aside class="w-64 bg-indigo-500 text-white shadow-lg">

        <div class="p-6 text-xl font-bold border-b border-indigo-500">
            Hotel Reservation
        </div>

        <nav class="flex-1 p-4 space-y-2">

            <a href="${pageContext.request.contextPath}/reservation.jsp"
               class="block px-4 py-3 rounded-lg hover:bg-indigo-600 transition">
                Make Reservation
            </a>

            <a href="${pageContext.request.contextPath}/reservation"
               class="block px-4 py-3 rounded-lg hover:bg-indigo-600 transition">
                Reservation List
            </a>

            <a href="${pageContext.request.contextPath}/guests"
               class="block px-4 py-3 rounded-lg hover:bg-indigo-600 transition">
                Guests
            </a>

            <a href="${pageContext.request.contextPath}/rooms"
               class="block px-4 py-3 rounded-lg hover:bg-indigo-600 transition">
                Rooms
            </a>

            <a href="${pageContext.request.contextPath}/help"
               class="block px-4 py-3 rounded-lg hover:bg-indigo-600 transition">
                Help Guide
            </a>

            <% if ("ADMIN".equals(session.getAttribute("role"))) { %>

            <a href="${pageContext.request.contextPath}/users"
               class="block px-4 py-3 rounded-lg hover:bg-indigo-600 transition">
                User Management
            </a>

            <a href="${pageContext.request.contextPath}/reports"
               class="block px-4 py-3 rounded-lg hover:bg-indigo-600 transition">
                Reports
            </a>

            <div class="px-4 py-3 rounded-lg bg-indigo-600">
                System Settings
            </div>

            <% } %>

        </nav>

    </aside>

    <!-- ===== MAIN CONTENT ===== -->
    <div class="flex-1 flex flex-col bg-white">

        <!-- ===== TOP NAVBAR ===== -->
        <header class="bg-white shadow-sm border-b px-6 py-4 flex justify-between items-center">

            <h2 class="text-xl font-semibold text-gray-700">
                Dashboard
            </h2>

            <div class="flex items-center gap-4">

                <span class="text-gray-600">
                    Welcome,
                    <b><%= session.getAttribute("username") %></b>
                    (<%= session.getAttribute("role") %>)
                </span>

                <form action="${pageContext.request.contextPath}/logout" method="post">
                    <button class="bg-red-500 text-white px-4 py-2 rounded-lg hover:bg-red-600 transition">
                        Logout
                    </button>
                </form>

            </div>
        </header>

        <main class="p-10 max-w-7xl mx-auto">

            <!-- Welcome Section -->
            <div class="mb-12">
                <h1 class="text-3xl font-bold text-gray-800">
                    Welcome back, <%= session.getAttribute("username") %>
                </h1>
                <p class="text-gray-500 mt-3 text-lg">
                    Here’s what’s happening in your hotel today.
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


        </main>



    </div>

</div>

<script>
    feather.replace()
</script>

</body>
</html>
