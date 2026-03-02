<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page session="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Guests | Hotel Reservation</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>

<body class="bg-gray-100 font-sans">

<div class="flex h-screen">

    <jsp:include page="includes/sidebar.jsp" />
        <!-- ===== PAGE CONTENT ===== -->
        <main class="flex-1 overflow-y-auto p-10">

            <div class="bg-white rounded-3xl shadow-xl p-8">

                <!-- PAGE HEADER -->
                <div class="mb-8">
                    <h1 class="text-2xl font-bold text-gray-800">
                        Registered Guests
                    </h1>
                    <p class="text-gray-500 mt-2">
                        View and manage registered guests
                    </p>
                </div>

                <!-- SEARCH BAR -->
                <form method="get"
                      action="${pageContext.request.contextPath}/guests"
                      class="flex gap-4 mb-8">

                    <input type="text"
                           name="search"
                           value="${param.search}"
                           placeholder="Search by name or phone"
                           class="px-4 py-3 border rounded-xl focus:ring-2 focus:ring-indigo-500 w-72">

                    <button type="submit"
                            class="bg-indigo-600 text-white px-6 py-3 rounded-xl hover:bg-indigo-700 transition">
                        Search
                    </button>
                </form>

                <!-- TABLE -->
                <div class="overflow-x-auto">
                    <table class="min-w-full text-sm text-left border-collapse">

                        <thead class="bg-gray-100 text-gray-600 uppercase text-xs">
                        <tr>
                            <th class="px-6 py-4">ID</th>
                            <th class="px-6 py-4">Name</th>
                            <th class="px-6 py-4">Phone</th>
                            <th class="px-6 py-4">Email</th>
                            <th class="px-6 py-4">Address</th>
                            <th class="px-6 py-4">Action</th>
                        </tr>
                        </thead>

                        <tbody class="divide-y">

                        <c:choose>

                            <c:when test="${empty guests}">
                                <tr>
                                    <td colspan="6"
                                        class="text-center py-10 text-gray-400">
                                        No guests found
                                    </td>
                                </tr>
                            </c:when>

                            <c:otherwise>
                                <c:forEach items="${guests}" var="g">
                                    <tr class="hover:bg-gray-50 transition">

                                        <td class="px-6 py-4">${g.guestId}</td>
                                        <td class="px-6 py-4 font-medium">${g.name}</td>
                                        <td class="px-6 py-4">${g.phone}</td>
                                        <td class="px-6 py-4">${g.email}</td>
                                        <td class="px-6 py-4">${g.address}</td>

                                        <td class="px-6 py-4">
                                            <a href="${pageContext.request.contextPath}/guest-edit?id=${g.guestId}"
                                               class="bg-indigo-500 text-white px-4 py-2 rounded-lg hover:bg-indigo-600 transition">
                                                Edit
                                            </a>
                                        </td>

                                    </tr>
                                </c:forEach>
                            </c:otherwise>

                        </c:choose>

                        </tbody>
                    </table>
                </div>

            </div>

        </main>

    </div>

</div>

</body>
</html>