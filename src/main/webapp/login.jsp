<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2/1/2026
  Time: 3:01 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login | Hotel Reservation</title>

    <!-- Tailwind CDN -->
    <script src="https://cdn.tailwindcss.com"></script>
</head>

<body class="min-h-screen flex items-center justify-center bg-gradient-to-br from-indigo-600 via-purple-600 to-pink-500">

<div class="bg-white shadow-2xl rounded-3xl p-10 w-full max-w-md">

    <!-- Logo / Title -->
    <div class="text-center mb-8">
        <h2 class="text-3xl font-bold text-gray-800">
            Ocean View Resort
        </h2>
        <p class="text-gray-500 mt-2">
            Sign in to access your dashboard
        </p>
    </div>

    <!-- Login Form -->
    <form action="${pageContext.request.contextPath}/login" method="post" class="space-y-6">

        <div>
            <label class="block text-gray-600 text-sm font-medium mb-2">
                Username
            </label>
            <input
                    type="text"
                    name="username"
                    required
                    class="w-full px-4 py-3 border rounded-xl focus:ring-2 focus:ring-indigo-500 focus:outline-none transition"
                    placeholder="Enter your username"
            >
        </div>

        <div>
            <label class="block text-gray-600 text-sm font-medium mb-2">
                Password
            </label>
            <div class="relative">
            <input
                    type="password"
                    id = "password"
                    name="password"
                    required
                    class="w-full px-4 py-3 border rounded-xl focus:ring-2 focus:ring-indigo-500 focus:outline-none transition"
                    placeholder="Enter your password"
            >

            <!-- Eye Icon -->
            <button type="button"
                    onclick="togglePassword()"
                    class="absolute right-4 top-1/2 -translate-y-1/2 text-gray-500 hover:text-indigo-600">

                <!-- Eye Open -->
                <svg id="eyeOpen" xmlns="http://www.w3.org/2000/svg"
                     fill="none" viewBox="0 0 24 24"
                     stroke-width="1.5" stroke="currentColor"
                     class="w-5 h-5">
                    <path stroke-linecap="round" stroke-linejoin="round"
                          d="M2.25 12s3.75-7.5 9.75-7.5
                         9.75 7.5 9.75 7.5-3.75
                         7.5-9.75 7.5S2.25 12
                         2.25 12z"/>
                    <circle cx="12" cy="12" r="3"/>
                </svg>

                <!-- Eye Closed -->
                <svg id="eyeClosed" xmlns="http://www.w3.org/2000/svg"
                     fill="none" viewBox="0 0 24 24"
                     stroke-width="1.5" stroke="currentColor"
                     class="w-5 h-5 hidden">
                    <path stroke-linecap="round" stroke-linejoin="round"
                          d="M3 3l18 18M10.584 10.587a3 3 0 004.242 4.242"/>
                </svg>

            </button>

        </div>
        </div>

        <!-- Error Message -->
        <c:if test="${not empty error}">
            <div class="bg-red-100 text-red-600 text-sm p-3 rounded-lg">
                    ${error}
            </div>
        </c:if>

        <button
                type="submit"
                class="w-full bg-indigo-600 text-white py-3 rounded-xl font-semibold hover:bg-indigo-700 transition duration-300 shadow-md"
        >
            Login
        </button>

    </form>

</div>

<script>
    function togglePassword() {
        const passwordInput = document.getElementById("password");
        const eyeOpen = document.getElementById("eyeOpen");
        const eyeClosed = document.getElementById("eyeClosed");

        if (passwordInput.type === "password") {
            passwordInput.type = "text";
            eyeOpen.classList.add("hidden");
            eyeClosed.classList.remove("hidden");
        } else {
            passwordInput.type = "password";
            eyeOpen.classList.remove("hidden");
            eyeClosed.classList.add("hidden");
        }
    }
</script>


</body>
</html>