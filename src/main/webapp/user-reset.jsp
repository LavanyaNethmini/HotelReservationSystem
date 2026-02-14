<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2/14/2026
  Time: 2:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Reset Password</title>
</head>
<body>

<h2>Reset Password</h2>

<form method="post" action="user-reset">
    <input type="hidden" name="userId" value="${userId}"/>

    <label>New Password:</label>
    <input type="password" name="password" required/>

    <button type="submit">Reset</button>
</form>

</body>
</html>

