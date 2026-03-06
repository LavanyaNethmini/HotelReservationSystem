<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 3/6/2026
  Time: 11:25 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<<!DOCTYPE html>
<html>
<head>
    <title>Database Connection Error</title>

    <style>
        body{
            font-family: Arial, sans-serif;
            background:#f4f6fb;
            display:flex;
            justify-content:center;
            align-items:center;
            height:100vh;
        }

        .card{
            background:white;
            padding:40px;
            border-radius:10px;
            text-align:center;
            box-shadow:0 5px 20px rgba(0,0,0,0.1);
            width:420px;
        }

        h1{
            color:#e74c3c;
            margin-bottom:10px;
        }

        p{
            color:#555;
            margin-bottom:20px;
        }

        a{
            background:#5b5bd6;
            color:white;
            padding:10px 20px;
            border-radius:6px;
            text-decoration:none;
        }

        a:hover{
            background:#4545c4;
        }
    </style>
</head>

<body>

<div class="card">

    <h1>Database Connection Error</h1>

    <p>
        The system cannot connect to the database at the moment.
        Please try again later or contact the system administrator.
    </p>

    <a href="login.jsp">Back to Login</a>

</div>

</body>
</html>