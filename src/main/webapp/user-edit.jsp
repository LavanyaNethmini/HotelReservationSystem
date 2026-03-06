<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2/28/2026
  Time: 8:20 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page session="true" %>

<!DOCTYPE html>
<html>
<head>
    <title>Edit User | Hotel Reservation</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>

<body class="bg-gray-100 font-sans">

<div class="flex h-screen">

    <jsp:include page="includes/sidebar.jsp" />

        <!-- ===== PAGE CONTENT ===== -->
        <main class="flex-1 overflow-y-auto p-10">

            <div class="max-w-2xl mx-auto bg-white shadow-xl rounded-3xl p-10">

                <div class="mb-8">
                    <h1 class="text-2xl font-bold text-gray-800">
                        Update User Information
                    </h1>
                    <p class="text-gray-500 mt-2">
                        Modify the selected user account details.
                    </p>
                </div>

                <%
                    String error = (String) request.getAttribute("error");
                    if(error != null){
                %>

                <div style="color:red; font-weight:bold;">
                    <%= error %>
                </div>

                <%
                    }
                %>

                <form method="post"
                      action="${pageContext.request.contextPath}/user-edit"
                      class="space-y-6">

                    <!-- Hidden ID -->
                    <input type="hidden" name="userId"
                           value="${user.userId}"/>

                    <!-- Username -->
                    <div>
                        <label class="block text-sm font-medium text-gray-600 mb-2">
                            Username
                        </label>
                        <input type="text"
                               name="username"
                               value="${user.username}"
                               required
                               class="w-full px-4 py-3 border rounded-xl focus:ring-2 focus:ring-indigo-500 focus:outline-none transition">
                    </div>

                    <!-- Full Name -->
                    <div>
                        <label class="block text-sm font-medium text-gray-600 mb-2">
                            Full Name
                        </label>
                        <input type="text"
                               name="fullName"
                               value="${user.fullName}"
                               class="w-full px-4 py-3 border rounded-xl focus:ring-2 focus:ring-indigo-500 focus:outline-none transition">
                    </div>

                    <!-- Contact Number -->
                    <div>
                        <label class="block text-sm font-medium text-gray-600 mb-2">
                            Contact Number
                        </label>
                        <input type="text"
                               name="contactNo"
                               value="${user.contactNo}"
                               class="w-full px-4 py-3 border rounded-xl focus:ring-2 focus:ring-indigo-500 focus:outline-none transition">
                    </div>

                    <!-- Address -->
                    <div>
                        <label class="block text-sm font-medium text-gray-600 mb-2">
                            Address
                        </label>
                        <input type="text"
                               name="address"
                               value="${user.address}"
                               class="w-full px-4 py-3 border rounded-xl focus:ring-2 focus:ring-indigo-500 focus:outline-none transition">
                    </div>

                    <!-- Role -->
                    <div>
                        <label class="block text-sm font-medium text-gray-600 mb-2">
                            Role
                        </label>

                        <select name="role"
                                class="w-full px-4 py-3 border rounded-xl focus:ring-2 focus:ring-indigo-500 focus:outline-none transition">

                            <option value="ADMIN"
                            ${user.role == 'ADMIN' ? 'selected' : ''}>
                                Admin
                            </option>

                            <option value="STAFF"
                            ${user.role == 'STAFF' ? 'selected' : ''}>
                                Staff
                            </option>

                        </select>
                    </div>

                    <!-- Active Status -->
                    <div>
                        <label class="block text-sm font-medium text-gray-600 mb-2">
                            Account Status
                        </label>

                        <select name="active"
                                class="w-full px-4 py-3 border rounded-xl focus:ring-2 focus:ring-indigo-500 focus:outline-none transition">

                            <option value="true"
                            ${user.active ? 'selected' : ''}>
                                Active
                            </option>

                            <option value="false"
                            ${!user.active ? 'selected' : ''}>
                                Inactive
                            </option>

                        </select>
                    </div>

                    <!-- Buttons -->
                    <div class="flex gap-4 pt-4">

                        <button type="submit"
                                class="flex-1 bg-indigo-600 text-white py-3 rounded-xl hover:bg-indigo-700 transition shadow-lg">
                            Update User
                        </button>

                        <a href="${pageContext.request.contextPath}/users"
                           class="flex-1 bg-gray-200 text-gray-700 py-3 rounded-xl text-center hover:bg-gray-300 transition">
                            Cancel
                        </a>

                    </div>

                </form>

            </div>

        </main>

    </div>

</div>

</body>
</html>
