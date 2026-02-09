<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Guests</title>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/assets/css/style.css">
</head>

<body>

<!-- ===== PAGE CONTENT ===== -->
<div class="dashboard-container">
    <div class="dashboard-card">

        <h2>Guests</h2>
        <p class="page-subtitle">
            View and manage registered guests
        </p>

        <!-- SEARCH BAR -->
        <form method="get"
              action="${pageContext.request.contextPath}/guests"
              class="filter-bar">

            <input type="text"
                   name="search"
                   value="${param.search}"
                   placeholder="Search by name or phone">

            <button type="submit" class="btn-small">
                Search
            </button>
        </form>

        <!-- TABLE -->
        <div class="table-wrapper">
            <table class="modern-table">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Phone</th>
                    <th>Email</th>
                    <th>Address</th>
                    <th>Action</th>
                </tr>
                </thead>

                <tbody>
                <c:choose>
                    <c:when test="${empty guests}">
                        <tr>
                            <td colspan="6" class="no-data">
                                No guests found
                            </td>
                        </tr>
                    </c:when>

                    <c:otherwise>
                        <c:forEach items="${guests}" var="g">
                            <tr>
                                <td>${g.guestId}</td>
                                <td>${g.name}</td>
                                <td>${g.phone}</td>
                                <td>${g.email}</td>
                                <td>${g.address}</td>
                                <td>
                                    <a class="edit-btn"
                                       href="${pageContext.request.contextPath}/guest-edit?id=${g.guestId}">
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
</div>

</body>
</html>
