<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2/4/2026
  Time: 5:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page session="true" %>

<html>
<head>
    <title>Help Guide</title>
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
    <div class="dashboard-card help-card">

        <h2>Help & User Guide</h2>

        <p class="help-intro">
            This guide will help you understand how to use the Hotel Reservation System efficiently.
        </p>

        <!-- Dynamic help content -->
        <div class="help-content">
            <%= request.getAttribute("helpContent") %>
        </div>

        <div class="help-footer">
            <p>Need more help? Please contact the system administrator.</p>
        </div>

    </div>
</div><script>
    const headers = document.querySelectorAll(".accordion-header");

    headers.forEach(header => {
        header.addEventListener("click", () => {
            header.classList.toggle("active");

            const body = header.nextElementSibling;

            if (body.style.display === "block") {
                body.style.display = "none";
            } else {
                body.style.display = "block";
            }
        });
    });
</script>

</body>
</html>
