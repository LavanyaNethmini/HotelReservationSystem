<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page session="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Add Room | Hotel Reservation</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>

<body class="bg-gray-100 font-sans">

<div class="flex h-screen">

    <jsp:include page="includes/sidebar.jsp" />

        <!-- ===== PAGE CONTENT ===== -->
        <main class="flex-1 overflow-y-auto p-10">

            <div class="max-w-2xl mx-auto bg-white shadow-xl rounded-3xl p-10">

                <h1 class="text-2xl font-bold text-gray-800 mb-8">
                    Create a New Room
                </h1>

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
                      action="${pageContext.request.contextPath}/room-add"
                      class="space-y-6">

                    <!-- Room Number -->
                    <div>
                        <label class="block text-sm font-medium text-gray-600 mb-2">
                            Room Number
                        </label>
                        <input type="text"
                               name="roomNumber"
                               required
                               class="w-full px-4 py-3 border rounded-xl focus:ring-2 focus:ring-indigo-500 focus:outline-none transition">
                    </div>

                    <!-- Room Type -->
                    <div>
                        <label class="block text-sm font-medium text-gray-600 mb-2">
                            Room Type
                        </label>
                        <select name="roomType"
                                required
                                class="w-full px-4 py-3 border rounded-xl focus:ring-2 focus:ring-indigo-500 focus:outline-none transition">

                            <option value="SINGLE">Single</option>
                            <option value="DOUBLE">Double</option>
                            <option value="SUITE">Suite</option>

                        </select>
                    </div>

                    <!-- Price -->
                    <div>
                        <label class="block text-sm font-medium text-gray-600 mb-2">
                            Price per Night (LKR)
                        </label>
                        <input type="number"
                               step="0.01"
                               name="price_per_night"
                               required
                               class="w-full px-4 py-3 border rounded-xl focus:ring-2 focus:ring-indigo-500 focus:outline-none transition">
                    </div>

                    <!-- Buttons -->
                    <div class="pt-4 flex gap-4">

                        <button type="submit"
                                class="bg-indigo-600 text-white px-6 py-3 rounded-xl hover:bg-indigo-700 transition shadow-lg">
                            Save Room
                        </button>

                        <a href="rooms"
                           class="bg-gray-200 text-gray-700 px-6 py-3 rounded-xl hover:bg-gray-300 transition">
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