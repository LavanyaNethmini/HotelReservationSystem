<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page session="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>User Management | Hotel Reservation</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>

<body class="bg-gray-100 font-sans">

<div class="flex h-screen">

    <jsp:include page="includes/sidebar.jsp" />
        <!-- ===== PAGE CONTENT ===== -->
        <main class="flex-1 overflow-y-auto p-10">

            <div class="bg-white rounded-3xl shadow-xl p-8">

                <!-- Breadcrumb -->
                <c:if test="${not empty breadcrumbs}">
                    <div class="text-sm text-gray-500 mb-6">
                        <c:forEach var="b" items="${breadcrumbs}" varStatus="loop">
                            <c:choose>
                                <c:when test="${breadcrumbLinks[loop.index] != null}">
                                    <a href="${breadcrumbLinks[loop.index]}"
                                       class="hover:text-indigo-600">
                                            ${b}
                                    </a>
                                </c:when>
                                <c:otherwise>
                                    <span class="text-gray-700 font-medium">
                                            ${b}
                                    </span>
                                </c:otherwise>
                            </c:choose>

                            <c:if test="${!loop.last}">
                                <span class="mx-2">›</span>
                            </c:if>
                        </c:forEach>
                    </div>
                </c:if>

                <!-- Header Row -->
                <div class="flex justify-between items-center mb-8">
                    <div>
                        <h1 class="text-2xl font-bold text-gray-800">
                            Manage System Users
                        </h1>
                        <p class="text-gray-500 mt-1">
                            Add, update, or manage user accounts
                        </p>
                    </div>

                    <a href="user-add"
                       class="bg-indigo-600 text-white px-6 py-3 rounded-xl hover:bg-indigo-700 transition shadow-lg">
                        + Add User
                    </a>
                </div>

                <!-- Success Message -->
                <c:if test="${not empty sessionScope.success}">
                    <div class="mb-6 bg-green-100 text-green-700 px-6 py-4 rounded-xl">
                            ${sessionScope.success}
                    </div>
                    <c:remove var="success" scope="session"/>
                </c:if>

                <!-- TABLE -->
                <div class="overflow-x-auto">
                    <table class="min-w-full text-sm text-left">

                        <thead class="bg-gray-100 text-gray-600 uppercase text-xs">
                        <tr>
                            <th class="px-6 py-4">ID</th>
                            <th class="px-6 py-4">Username</th>
                            <th class="px-6 py-4">Role</th>
                            <th class="px-6 py-4">Status</th>
                            <th class="px-6 py-4">Actions</th>
                        </tr>
                        </thead>

                        <tbody class="divide-y">

                        <c:choose>

                            <c:when test="${empty users}">
                                <tr>
                                    <td colspan="5"
                                        class="text-center py-10 text-gray-400">
                                        No users found
                                    </td>
                                </tr>
                            </c:when>

                            <c:otherwise>
                                <c:forEach items="${users}" var="u">
                                    <tr class="hover:bg-gray-50 transition">

                                        <td class="px-6 py-4">
                                                ${u.userId}
                                        </td>

                                        <td class="px-6 py-4 font-medium">
                                                ${u.username}
                                        </td>

                                        <!-- ROLE BADGE -->
                                        <td class="px-6 py-4">
                                            <span class="px-3 py-1 rounded-full text-xs font-semibold
                                                ${u.role == 'ADMIN' ? 'bg-purple-100 text-purple-600'
                                                : 'bg-blue-100 text-blue-600'}">
                                                    ${u.role}
                                            </span>
                                        </td>

                                        <!-- STATUS BADGE -->
                                        <td class="px-6 py-4">
                                            <c:choose>
                                                <c:when test="${u.active}">
                                                    <span class="px-3 py-1 rounded-full text-xs font-semibold bg-green-100 text-green-600">
                                                        ACTIVE
                                                    </span>
                                                </c:when>
                                                <c:otherwise>
                                                    <span class="px-3 py-1 rounded-full text-xs font-semibold bg-red-100 text-red-600">
                                                        INACTIVE
                                                    </span>
                                                </c:otherwise>
                                            </c:choose>
                                        </td>

                                        <!-- ACTIONS -->
                                        <td class="px-6 py-4 flex gap-3 flex-wrap">

                                            <a href="user-edit?id=${u.userId}"
                                               class="text-indigo-600 hover:underline">
                                                Edit
                                            </a>

                                            <a href="user-delete?id=${u.userId}"
                                               onclick="return confirm('Are you sure you want to delete this user?');"
                                               class="text-red-600 hover:underline">
                                                Delete
                                            </a>

                                            <a href="user-reset?id=${u.userId}"
                                               class="text-gray-600 hover:underline">
                                                Reset Password
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