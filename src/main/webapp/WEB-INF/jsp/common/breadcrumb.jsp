<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2/9/2026
  Time: 8:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>

<div class="breadcrumb">
  <a href="${pageContext.request.contextPath}/dashboard.jsp">Dashboard</a>

  <%
    String[] crumbs = (String[]) request.getAttribute("breadcrumbs");
    String[] links  = (String[]) request.getAttribute("breadcrumbLinks");

    if (crumbs != null) {
      for (int i = 0; i < crumbs.length; i++) {
  %>
  <span class="separator">›</span>

  <% if (links != null && links.length > i && links[i] != null) { %>
  <a href="<%= links[i] %>"><%= crumbs[i] %></a>
  <% } else { %>
  <span class="current"><%= crumbs[i] %></span>
  <% } %>

  <%
      }
    }
  %>
</div>

