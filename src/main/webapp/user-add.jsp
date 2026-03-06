<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page session="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
  <title>Add User | Hotel Reservation</title>
  <script src="https://cdn.tailwindcss.com"></script>
</head>

<body class="bg-gray-100 font-sans">

<div class="flex h-screen">

  <jsp:include page="includes/sidebar.jsp" />

    <!-- ===== PAGE CONTENT ===== -->
    <main class="flex-1 overflow-y-auto p-10">

      <div class="max-w-3xl mx-auto bg-white shadow-xl rounded-3xl p-10">

        <div class="mb-8">
          <h1 class="text-2xl font-bold text-gray-800">
            Create New User Account
          </h1>
          <p class="text-gray-500 mt-2">
            Fill in the details below to register a new system user.
          </p>
        </div>

        <form method="post"
              action="${pageContext.request.contextPath}/user-add"
              class="grid grid-cols-1 md:grid-cols-2 gap-6">

          <!-- Username -->
          <div>
            <label class="block text-sm font-medium text-gray-600 mb-2">
              Username
            </label>
            <input type="text" name="username" required
                   class="w-full px-4 py-3 border rounded-xl focus:ring-2 focus:ring-indigo-500 focus:outline-none transition">
          </div>

          <!-- Password -->
          <div>
            <label class="block text-sm font-medium text-gray-600 mb-2">
              Password
            </label>
            <input type="password" name="password" required
                   class="w-full px-4 py-3 border rounded-xl focus:ring-2 focus:ring-indigo-500 focus:outline-none transition">
          </div>

          <!-- Full Name -->
          <div>
            <label class="block text-sm font-medium text-gray-600 mb-2">
              Full Name
            </label>
            <input type="text" name="fullName"
                   class="w-full px-4 py-3 border rounded-xl focus:ring-2 focus:ring-indigo-500 focus:outline-none transition">
          </div>

          <!-- Contact No -->
          <div>
            <label class="block text-sm font-medium text-gray-600 mb-2">
              Contact Number
            </label>
            <input type="text" name="contactNo"
                   class="w-full px-4 py-3 border rounded-xl focus:ring-2 focus:ring-indigo-500 focus:outline-none transition">
          </div>

          <!-- Address -->
          <div class="md:col-span-2">
            <label class="block text-sm font-medium text-gray-600 mb-2">
              Address
            </label>
            <input type="text" name="address"
                   class="w-full px-4 py-3 border rounded-xl focus:ring-2 focus:ring-indigo-500 focus:outline-none transition">
          </div>

          <!-- Role -->
          <div class="md:col-span-2">
            <label class="block text-sm font-medium text-gray-600 mb-2">
              Role
            </label>
            <select name="role" required
                    class="w-full px-4 py-3 border rounded-xl focus:ring-2 focus:ring-indigo-500 focus:outline-none transition">
              <option value="ADMIN">Admin</option>
              <option value="STAFF">Staff</option>
            </select>
          </div>

          <!-- Buttons -->
          <div class="md:col-span-2 flex gap-4 pt-4">

            <button type="submit"
                    class="bg-indigo-600 text-white px-8 py-3 rounded-xl hover:bg-indigo-700 transition shadow-lg">
              Save User
            </button>

            <a href="users"
               class="bg-gray-200 text-gray-700 px-8 py-3 rounded-xl hover:bg-gray-300 transition">
              Cancel
            </a>

          </div>

          <% if (request.getAttribute("error") != null) { %>
          <div class="bg-red-100 text-red-600 p-4 rounded-lg mb-6">
            <%= request.getAttribute("error") %>
          </div>
          <% } %>



        </form>

      </div>

    </main>

  </div>

</div>

</body>
</html>