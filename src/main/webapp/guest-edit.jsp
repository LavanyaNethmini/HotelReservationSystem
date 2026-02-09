<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2/9/2026
  Time: 11:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Edit Guest</title>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/assets/css/style.css">
</head>
<body>

<div class="dashboard-container">
    <div class="dashboard-card">

        <h2>Edit Guest</h2>

        <form method="post" action="guest-edit">

            <input type="hidden" name="guestId" value="${guest.guestId}"/>

            <div class="input-group">
                <label>Name</label>
                <input type="text" name="name" value="${guest.name}" required>
            </div>

            <div class="input-group">
                <label>Phone</label>
                <input type="text" name="phone" value="${guest.phone}" required>
            </div>

            <div class="input-group">
                <label>Email</label>
                <input type="email" name="email" value="${guest.email}">
            </div>

            <div class="input-group">
                <label>Address</label>
                <input type="text" name="address" value="${guest.address}">
            </div>

            <button class="btn">Update Guest</button>

        </form>

    </div>
</div>

</body>
</html>

