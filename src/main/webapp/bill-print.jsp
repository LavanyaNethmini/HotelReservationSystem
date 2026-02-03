<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2/3/2026
  Time: 11:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.hotel.reservation.domain.model.Bill" %>
<%
    Bill bill = (Bill) request.getAttribute("bill");
%>

<h2>Hotel Reservation Bill</h2>

<p>Reservation ID: <%= bill.getReservationId() %></p>
<p>Nights: <%= bill.getNights() %></p>
<p>Room Rate: <%= bill.getRoomRate() %></p>
<p>Total Amount: <b><%= bill.getTotalAmount() %></b></p>
<p>Payment Method: <%= bill.getPaymentMethod() %></p>

<button onclick="window.print()">Print Bill</button>

