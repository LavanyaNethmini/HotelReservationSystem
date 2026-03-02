<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2/14/2026
  Time: 2:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page session="true" %>

<!DOCTYPE html>
<html>
<head>
    <title>Reset Password | Hotel Reservation</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>

<body class="bg-gray-100 font-sans">

<div class="flex h-screen">

    <jsp:include page="includes/sidebar.jsp" />

        <!-- ===== PAGE CONTENT ===== -->
        <main class="flex-1 flex items-center justify-center p-10">

            <div class="w-full max-w-md bg-white shadow-xl rounded-3xl p-10">

                <div class="mb-8 text-center">
                    <h1 class="text-2xl font-bold text-gray-800">
                        Reset Password
                    </h1>
                    <p class="text-gray-500 mt-2">
                        Enter a new password for this user account.
                    </p>
                </div>

                <form method="post"
                      action="${pageContext.request.contextPath}/user-reset"
                      class="space-y-6">

                    <input type="hidden" name="userId" value="${userId}"/>

                    <div>
                        <label class="block text-sm font-medium text-gray-600 mb-2">
                            New Password
                        </label>
                        <input type="password"
                               name="password"
                               required
                               class="w-full px-4 py-3 border rounded-xl focus:ring-2 focus:ring-indigo-500 focus:outline-none transition">
                    </div>

                    <div class="flex gap-4 pt-4">

                        <button type="submit"
                                class="flex-1 bg-indigo-600 text-white py-3 rounded-xl hover:bg-indigo-700 transition shadow-lg">
                            Reset Password
                        </button>

                        <a href="users"
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