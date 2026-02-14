<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2/13/2026
  Time: 10:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
  <title>Add User</title>
  <link rel="stylesheet"
        href="${pageContext.request.contextPath}/assets/css/style.css">
</head>

<body>

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

<div class="dashboard-container">
  <div class="dashboard-card">

    <h2>Add New User</h2>

    <form method="post" class="form-grid">

      <div class="input-group">
        <label>Username</label>
        <input type="text" name="username" required>
      </div>

      <div class="input-group">
        <label>Password</label>
        <input type="password" name="password" required>
      </div>

      <div class="input-group">
        <label>Full Name</label>
        <input type="text" name="fullName">
      </div>

      <div class="input-group">
        <label>Contact No</label>
        <input type="text" name="contactNo">
      </div>

      <div class="input-group">
        <label>Address</label>
        <input type="text" name="address">
      </div>

      <div class="input-group">
        <label>Role</label>
        <select name="role" required>
          <option value="ADMIN">ADMIN</option>
          <option value="STAFF">STAFF</option>
        </select>
      </div>

      <button type="submit" class="btn-small">
        Save User
      </button>

    </form>

  </div>
</div>

</body>
</html>
