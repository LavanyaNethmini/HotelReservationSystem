<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2/12/2026
  Time: 10:40 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>User Management</title>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/assets/css/style.css">
</head>

<body>

<!-- ===== NAVBAR ===== -->
<div class="navbar">
    <div class="logo">Hotel Reservation System</div>
    <div class="nav-right">
        <span class="username">
            <%= session.getAttribute("username") %>
        </span>
        <form action="logout" method="post">
            <button type="submit">Logout</button>
        </form>
    </div>
</div>

<!-- ===== CONTENT ===== -->
<div class="dashboard-container">
    <div class="dashboard-card">

        <!-- ===== BREADCRUMB ===== -->
        <c:if test="${not empty breadcrumbs}">
            <nav class="breadcrumb">
                <c:forEach var="b" items="${breadcrumbs}" varStatus="loop">
                    <c:choose>
                        <c:when test="${breadcrumbLinks[loop.index] != null}">
                            <a href="${breadcrumbLinks[loop.index]}">${b}</a>
                        </c:when>
                        <c:otherwise>
                            <span class="active">${b}</span>
                        </c:otherwise>
                    </c:choose>

                    <c:if test="${!loop.last}">
                        <span class="separator">›</span>
                    </c:if>
                </c:forEach>
            </nav>
        </c:if>

        <!-- ===== HEADER ===== -->
        <div style="display:flex; justify-content:space-between; align-items:center; margin-bottom:20px;">
            <h2>User Management</h2>

            <a href="user-add" class="btn-small">
                + Add User
            </a>
        </div>

        <!-- ===== SUCCESS MESSAGE ===== -->
        <c:if test="${not empty sessionScope.success}">
            <div class="success-msg">
                    ${sessionScope.success}
            </div>
            <c:remove var="success" scope="session"/>
        </c:if>

        <!-- ===== TABLE ===== -->
        <div class="table-wrapper">
            <table class="modern-table">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Username</th>
                    <th>Role</th>
                    <th>Status</th>
                    <th>Actions</th>
                </tr>
                </thead>

                <tbody>
                <c:choose>

                    <c:when test="${empty users}">
                        <tr>
                            <td colspan="5" class="no-data">
                                No users found
                            </td>
                        </tr>
                    </c:when>

                    <c:otherwise>
                        <c:forEach items="${users}" var="u">
                            <tr>
                                <td>${u.userId}</td>
                                <td>${u.username}</td>
                                <td>
                                    <span class="status ${u.role}">
                                            ${u.role}
                                    </span>
                                </td>
                                <td>
                                    <c:choose>
                                        <c:when test="${u.active}">
                                            <span class="status CONFIRMED">ACTIVE</span>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="status CANCELLED">INACTIVE</span>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td>
                                    <a href="user-edit?id=${u.userId}" class="action-link edit">
                                        Edit
                                    </a>

                                    <a href="user-delete?id=${u.userId}"
                                       class="action-link delete"
                                       onclick="return confirm('Are you sure you want to delete this user?');">
                                        Delete
                                    </a>

                                    <a href="user-reset?id=${u.userId}" class="action-link reset">
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
</div>

</body>
</html>
