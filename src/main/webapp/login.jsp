<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2/1/2026
  Time: 3:01 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Login | Hotel Reservation</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
</head>
<body>

<div class="card">
    <h2>Hotel Reservation Login</h2>

    <form action="${pageContext.request.contextPath}/login" method="post">

        <div class="input-group">
            <label>Username</label>
            <input type="text" name="username" required>
        </div>

        <div class="input-group">
            <label>Password</label>
            <input type="password" name="password" required>
        </div>

        <button class="btn" type="submit">Login</button>
    </form>

    <div class="error">${error}</div>
</div>

</body>
</html>

