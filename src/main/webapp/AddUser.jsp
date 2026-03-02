<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2/1/2026
  Time: 2:46 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="user" method="post">
    <input name="username" placeholder="Username" />
    <input name="password" type="password" placeholder="Password" />
    <input name="fullName" placeholder="Full Name" />
    <input name="contactNo" placeholder="Contact No" />
    <input name="address" placeholder="Address" />
    <select name="role">
        <option value="ADMIN">ADMIN</option>
        <option value="STAFF">STAFF</option>
    </select>
    <button type="submit">Register</button>
</form>

</body>
</html>
