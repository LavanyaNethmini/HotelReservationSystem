package com.hotel.reservation.presentation.servlet;

import com.hotel.reservation.domain.model.Bill;
import com.hotel.reservation.service.BillingService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Disabled;

@Disabled("Servlet test requires database - skipped for unit testing")
class BillingServletTest {



    @Test
    void doPost_ShouldGenerateBillAndForwardToInvoice() throws Exception {

        // mock objects
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        BillingService billingService = mock(BillingService.class);

        // servlet
        BillingServlet servlet = new BillingServlet();

        // inject mock service (avoid DB)
        java.lang.reflect.Field field =
                BillingServlet.class.getDeclaredField("billingService");
        field.setAccessible(true);
        field.set(servlet, billingService);

        // mock session
        when(request.getSession(false)).thenReturn(session);
        when(session.getAttribute("userId")).thenReturn(1);

        // mock request parameters
        when(request.getParameter("reservationId")).thenReturn("1");
        when(request.getParameter("roomRate")).thenReturn("5000");
        when(request.getParameter("paymentMethod")).thenReturn("CASH");
        when(request.getParameter("checkIn")).thenReturn("2024-01-01");
        when(request.getParameter("checkOut")).thenReturn("2024-01-03");

        when(request.getRequestDispatcher("bill-print.jsp"))
                .thenReturn(dispatcher);

        // execute servlet
        servlet.doPost(request, response);

        // verify service called
        verify(billingService).generateBill(any(Bill.class));

        // verify JSP forward
        verify(dispatcher).forward(request, response);

        // verify attribute set
        verify(request).setAttribute(eq("bill"), any(Bill.class));
    }
}