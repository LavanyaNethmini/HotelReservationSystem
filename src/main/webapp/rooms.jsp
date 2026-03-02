<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page session="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Rooms | Hotel Reservation</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>

<body class="bg-gray-100 font-sans">

<div class="flex h-screen">

    <jsp:include page="includes/sidebar.jsp" />

        <!-- ===== PAGE CONTENT ===== -->
        <main class="flex-1 overflow-y-auto p-10">

            <div class="bg-white rounded-3xl shadow-xl p-8">

                <!-- HEADER SECTION -->
                <div class="flex justify-between items-center mb-8">

                    <div>
                        <h1 class="text-2xl font-bold text-gray-800">
                            Room Management
                        </h1>
                        <p class="text-gray-500 mt-1">
                            Check availability and manage rooms
                        </p>
                    </div>

                    <c:if test="${sessionScope.role == 'ADMIN'}">
                        <a href="${pageContext.request.contextPath}/room-add"
                           class="bg-indigo-600 text-white px-6 py-3 rounded-xl hover:bg-indigo-700 transition shadow-lg">
                            + Add Room
                        </a>
                    </c:if>

                </div>

                <!-- FILTER BAR -->
                <form method="get"
                      action="${pageContext.request.contextPath}/rooms"
                      class="flex flex-wrap gap-4 mb-8">

                    <input type="date"
                           name="checkIn"
                           required
                           class="px-4 py-3 border rounded-xl focus:ring-2 focus:ring-indigo-500">

                    <input type="date"
                           name="checkOut"
                           required
                           class="px-4 py-3 border rounded-xl focus:ring-2 focus:ring-indigo-500">

                    <button type="submit"
                            class="bg-indigo-600 text-white px-6 py-3 rounded-xl hover:bg-indigo-700 transition">
                        Check Availability
                    </button>

                </form>

                <!-- TABLE -->
                <div class="overflow-x-auto">
                    <table class="min-w-full text-sm text-left border-collapse">

                        <thead class="bg-gray-100 text-gray-600 uppercase text-xs">
                        <tr>
                            <th class="px-6 py-4">Room No</th>
                            <th class="px-6 py-4">Type</th>
                            <th class="px-6 py-4">Price (LKR)</th>
                            <th class="px-6 py-4">Status</th>
                            <th class="px-6 py-4">Action</th>
                        </tr>
                        </thead>

                        <tbody class="divide-y">

                        <c:if test="${empty rooms}">
                            <tr>
                                <td colspan="5"
                                    class="text-center py-10 text-gray-400">
                                    No rooms found for selected dates
                                </td>
                            </tr>
                        </c:if>

                        <c:forEach items="${rooms}" var="room">
                            <tr class="hover:bg-gray-50 transition">

                                <td class="px-6 py-4 font-medium">
                                        ${room.roomNumber}
                                </td>

                                <td class="px-6 py-4">
                                        ${room.roomType}
                                </td>

                                <td class="px-6 py-4">
                                    LKR ${room.price}
                                </td>

                                <td class="px-6 py-4">
                                    <c:choose>
                                        <c:when test="${room.available}">
                                            <span class="px-3 py-1 rounded-full text-xs font-semibold bg-green-100 text-green-600">
                                                AVAILABLE
                                            </span>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="px-3 py-1 rounded-full text-xs font-semibold bg-red-100 text-red-600">
                                                NOT AVAILABLE
                                            </span>
                                        </c:otherwise>
                                    </c:choose>
                                </td>

                                <td class="px-6 py-4">
                                    <c:choose>
                                        <c:when test="${room.available}">
                                            <a href="reserve?roomId=${room.roomId}"
                                               class="bg-indigo-500 text-white px-4 py-2 rounded-lg hover:bg-indigo-600 transition">
                                                Reserve
                                            </a>
                                        </c:when>
                                        <c:otherwise>
                                            <button disabled
                                                    class="bg-gray-300 text-gray-600 px-4 py-2 rounded-lg cursor-not-allowed">
                                                Unavailable
                                            </button>
                                        </c:otherwise>
                                    </c:choose>
                                </td>

                            </tr>
                        </c:forEach>

                        </tbody>

                    </table>
                </div>

            </div>

        </main>

    </div>

</div>

</body>
</html>