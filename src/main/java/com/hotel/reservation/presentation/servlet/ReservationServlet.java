package com.hotel.reservation.presentation.servlet;

import com.hotel.reservation.domain.model.Bill;
import com.hotel.reservation.domain.model.Guest;
import com.hotel.reservation.domain.model.Reservation;
import com.hotel.reservation.repository.GuestRepository;
import com.hotel.reservation.repository.impl.GuestRepositoryImpl;
import com.hotel.reservation.service.BillingService;
import com.hotel.reservation.service.ReservationService;
import com.hotel.reservation.service.impl.BillingServiceImpl;
import com.hotel.reservation.service.impl.ReservationServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class ReservationServlet extends HttpServlet {

    private final ReservationService reservationService =
            new ReservationServiceImpl();

    private final BillingService billingService =
            new BillingServiceImpl();

    private final GuestRepository guestRepository =
            new GuestRepositoryImpl();

    /* =========================
       AJAX: FIND GUEST BY PHONE
       ========================= */
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws IOException {

        String phone = request.getParameter("phone");
        if (phone == null) return;

        Guest guest = guestRepository.findByPhone(phone);

        if (guest != null) {
            response.setContentType("application/json");
            response.getWriter().write(
                    "{"
                            + "\"name\":\"" + guest.getName() + "\","
                            + "\"address\":\"" + guest.getAddress() + "\","
                            + "\"email\":\"" + guest.getEmail() + "\""
                            + "}"
            );
        }
    }

    /* =========================
       SAVE RESERVATION + BILL
       ========================= */
    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userId") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        int userId = (int) session.getAttribute("userId");

        try {
            // ===== GUEST =====
            Guest guest = new Guest(
                    request.getParameter("name"),
                    request.getParameter("address"),
                    request.getParameter("email"),
                    request.getParameter("phone")
            );

            // ===== RESERVATION =====
            LocalDate checkIn =
                    LocalDate.parse(request.getParameter("checkIn"));
            LocalDate checkOut =
                    LocalDate.parse(request.getParameter("checkOut"));

            Reservation reservation =
                    new Reservation.Builder()
                            .roomId(Integer.parseInt(request.getParameter("roomId")))
                            .checkIn(checkIn)
                            .checkOut(checkOut)
                            .createdBy(userId)
                            .build(); // date validation here

            // Save reservation & guest
            int reservationId =
                    reservationService.makeReservation(guest, reservation);
            // ⚠️ makeReservation must return reservationId (small change)

            // ===== BILLING =====
            String paymentMethod =
                    request.getParameter("paymentMethod");

            long nights =
                    ChronoUnit.DAYS.between(checkIn, checkOut);

            // Temporary room rate (later we’ll fetch from DB)
            BigDecimal roomRate = new BigDecimal("5000.00");

            Bill bill =
                    new Bill.Builder()
                            .reservationId(reservationId)
                            .nights((int) nights)
                            .roomRate(roomRate)
                            .paymentMethod(paymentMethod)
                            .createdBy(userId)
                            .build();

            billingService.generateBill(bill);

            // Forward to printable bill
            request.setAttribute("bill", bill);
            request.getRequestDispatcher("bill-print.jsp")
                    .forward(request, response);

        } catch (IllegalStateException e) {

            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("reservation.jsp")
                    .forward(request, response);

        } catch (RuntimeException e) {

            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("reservation.jsp")
                    .forward(request, response);
        }
    }
}
