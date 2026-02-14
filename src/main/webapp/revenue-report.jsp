<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2/14/2026
  Time: 6:18 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>Revenue Report</h2>

<form method="get" action="reports/revenue">
    <label>Start Date:</label>
    <input type="date" name="start">

    <label>End Date:</label>
    <input type="date" name="end">

    <button>Calculate</button>
</form>

<h3>Total Revenue: ${totalRevenue}</h3>

<hr>

<form method="get" action="reports/revenue">
    <label>Year:</label>
    <input type="number" name="year">

    <button>Monthly Report</button>
</form>

<table>
    <tr>
        <th>Month</th>
        <th>Revenue</th>
    </tr>

    <c:forEach items="${monthlyRevenue}" var="r">
        <tr>
            <td>${r.month}</td>
            <td>${r.totalRevenue}</td>
        </tr>
    </c:forEach>
</table>


</body>
</html>
