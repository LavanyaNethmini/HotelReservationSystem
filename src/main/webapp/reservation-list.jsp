<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
  <title>Reservations</title>
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

    <!-- ===== PAGE HEADER ===== -->
    <div class="page-header">
      <h2>Reservations</h2>

      <!-- BREADCRUMB -->
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
    </div>

    <!-- ===== SUCCESS MESSAGE ===== -->
    <c:if test="${not empty sessionScope.success}">
      <div class="success-msg">
          ${sessionScope.success}
      </div>
      <c:remove var="success" scope="session"/>
    </c:if>

    <!-- ===== FILTER BAR ===== -->
    <form method="get"
          action="${pageContext.request.contextPath}/reservation"
          class="filter-bar">

      <input type="text"
             name="search"
             placeholder="Search by guest name or phone">

      <input type="month" name="month">

      <button type="submit" class="btn-small">
        Filter
      </button>
    </form>

    <!-- ===== TABLE ===== -->
    <div class="table-wrapper">
      <table class="modern-table">
        <thead>
        <tr>
          <th>ID</th>
          <th>Guest</th>
          <th>Phone</th>
          <th>Room</th>
          <th>Check In</th>
          <th>Check Out</th>
          <th>Status</th>
          <th>Action</th>
        </tr>
        </thead>

        <tbody>
        <c:forEach items="${reservations}" var="r">
          <tr>
            <td>${r.reservationId}</td>
            <td>${r.guestName}</td>
            <td>${r.guestPhone}</td>
            <td>${r.roomId}</td>
            <td>${r.checkIn}</td>
            <td>${r.checkOut}</td>
            <td>
                            <span class="status ${r.status}">
                                ${r.status}
                            </span>
            </td>
            <td>
              <a class="cancel-btn danger"
                 href="cancel-reservation?id=${r.reservationId}"
                 onclick="return confirm('Cancel this reservation?');">
                Cancel
              </a>
            </td>
          </tr>
        </c:forEach>

        <c:if test="${empty reservations}">
          <tr>
            <td colspan="8" class="no-data">
              No reservations found
            </td>
          </tr>
        </c:if>

        </tbody>
      </table>
    </div>

  </div>
</div>

</body>
</html>
