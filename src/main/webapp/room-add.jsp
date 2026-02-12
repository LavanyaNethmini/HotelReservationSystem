<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2/12/2026
  Time: 7:59 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Add Room</title>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/assets/css/style.css">
</head>

<body>

<div class="dashboard-container">
    <div class="dashboard-card">

        <h2>Add New Room</h2>

        <form method="post"
              action="${pageContext.request.contextPath}/room-add">

            <div class="input-group">
                <label>Room Number</label>
                <input type="text" name="roomNumber" required>
            </div>

            <div class="input-group">
                <label>Room Type</label>
                <select name="roomType" required>
                    <option value="SINGLE">SINGLE</option>
                    <option value="DOUBLE">DOUBLE</option>
                    <option value="SUITE">SUITE</option>
                </select>
            </div>

            <div class="input-group">
                <label>Price</label>
                <input type="number" step="0.01" name="price_per_night" required>
            </div>

            <button class="btn">Save Room</button>

        </form>

    </div>
</div>

</body>
</html>
