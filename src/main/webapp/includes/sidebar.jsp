<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 3/1/2026
  Time: 6:50 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<!-- ===== SIDEBAR ===== -->
<aside class="w-64 bg-indigo-500 text-white shadow-lg">

    <div class="p-6 text-xl font-bold border-b border-indigo-500">
        Ocean View Resort
    </div>

    <nav class="flex-1 p-4 space-y-2">

        <a href="${pageContext.request.contextPath}/dashboard"
           class="flex items-center gap-3 px-4 py-3 rounded-lg hover:bg-indigo-600 transition">
            <svg xmlns="http://www.w3.org/2000/svg" fill="none"
                 viewBox="0 0 24 24" stroke-width="1.5"
                 stroke="currentColor" class="w-5 h-5">
                <path stroke-linecap="round" stroke-linejoin="round"
                      d="M3 9.75L12 3l9 6.75V20.25a.75.75 0 01-.75.75h-6.75v-6h-3v6H3.75a.75.75 0 01-.75-.75V9.75z"/>
            </svg>
            Home
        </a>

        <a href="${pageContext.request.contextPath}/reservation.jsp"
           class="flex items-center gap-3 px-4 py-3 rounded-lg hover:bg-indigo-600 transition">
            <svg xmlns="http://www.w3.org/2000/svg" fill="none"
                 viewBox="0 0 24 24" stroke-width="1.5"
                 stroke="currentColor" class="w-5 h-5">
                <path stroke-linecap="round" stroke-linejoin="round"
                      d="M8 7V3m8 4V3M4 11h16M5 21h14a1 1 0 001-1V8H4v12a1 1 0 001 1z"/>
            </svg>
            Make Reservation
        </a>

        <a href="${pageContext.request.contextPath}/reservation"
           class="flex items-center gap-3 px-4 py-3 rounded-lg hover:bg-indigo-600 transition">
            <svg xmlns="http://www.w3.org/2000/svg" fill="none"
                 viewBox="0 0 24 24" stroke-width="1.5"
                 stroke="currentColor" class="w-5 h-5">
                <path stroke-linecap="round" stroke-linejoin="round"
                      d="M3 7h18M3 12h18M3 17h18"/>
            </svg>
            Reservation List
        </a>

        <a href="${pageContext.request.contextPath}/guests"
           class="flex items-center gap-3 px-4 py-3 rounded-lg hover:bg-indigo-600 transition">
            <svg xmlns="http://www.w3.org/2000/svg" fill="none"
                 viewBox="0 0 24 24" stroke-width="1.5"
                 stroke="currentColor" class="w-5 h-5">
                <path stroke-linecap="round" stroke-linejoin="round"
                      d="M16 14a4 4 0 10-8 0m8 0a6 6 0 10-8 0"/>
            </svg>
            Guests
        </a>

        <a href="${pageContext.request.contextPath}/rooms"
           class="flex items-center gap-3 px-4 py-3 rounded-lg hover:bg-indigo-600 transition">
            <svg xmlns="http://www.w3.org/2000/svg" fill="none"
                 viewBox="0 0 24 24" stroke-width="1.5"
                 stroke="currentColor" class="w-5 h-5">
                <path stroke-linecap="round" stroke-linejoin="round"
                      d="M3 21h18M4 8h6v10H4z"/>
            </svg>
            Rooms
        </a>

        <a href="${pageContext.request.contextPath}/help"
           class="flex items-center gap-3 px-4 py-3 rounded-lg hover:bg-indigo-600 transition">

            <svg xmlns="http://www.w3.org/2000/svg" fill="none"
                 viewBox="0 0 24 24" stroke-width="1.5"
                 stroke="currentColor" class="w-5 h-5">
                <path stroke-linecap="round" stroke-linejoin="round"
                      d="M12 18h.01M9.09 9a3 3 0 115.82 1c0 2-3 2-3 4m0 4h.01"/>
            </svg>
            Help Guide
        </a>

        <% if ("ADMIN".equals(session.getAttribute("role"))) { %>

        <a href="${pageContext.request.contextPath}/users"
           class="flex items-center gap-3 px-4 py-3 rounded-lg hover:bg-indigo-600 transition">
            <svg xmlns="http://www.w3.org/2000/svg" fill="none"
                 viewBox="0 0 24 24" stroke-width="1.5"
                 stroke="currentColor" class="w-5 h-5">
                <path stroke-linecap="round" stroke-linejoin="round"
                      d="M15 12h.01M9 12h.01M12 15h.01M7 20h10"/>
            </svg>
            User Management
        </a>

        <a href="${pageContext.request.contextPath}/reports"
           class="flex items-center gap-3 px-4 py-3 rounded-lg hover:bg-indigo-600 transition">
            <svg xmlns="http://www.w3.org/2000/svg" fill="none"
                 viewBox="0 0 24 24" stroke-width="1.5"
                 stroke="currentColor" class="w-5 h-5">
                <path stroke-linecap="round" stroke-linejoin="round"
                      d="M3 3v18h18M9 17V9m4 8v-4m4 4v-6"/>
            </svg>
            Reports
        </a>

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
</body>
</html>
