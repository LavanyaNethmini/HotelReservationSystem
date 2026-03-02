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
import java.util.List;

public class ReservationServlet extends HttpServlet {

    private final ReservationService reservationService =
            new ReservationServiceImpl();

    private final BillingService billingService =
            new BillingServiceImpl();

    private final GuestRepository guestRepository =
            new GuestRepositoryImpl();

    /* =========================
       GET: AJAX / LIST / SEARCH
       ========================= */
    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp)
            throws ServletException, IOException {

        /* ===== AJAX: FIND GUEST BY PHONE ===== */
        String phone = req.getParameter("phone");
        if (phone != null) {
            Guest guest = guestRepository.findByPhone(phone);

            if (guest != null) {
                resp.setContentType("application/json");
                resp.getWriter().write(
                        "{"
                                + "\"name\":\"" + guest.getName() + "\","
                                + "\"address\":\"" + guest.getAddress() + "\","
                                + "\"email\":\"" + guest.getEmail() + "\""
                                + "}"
                );
            }
            return; // ⚠️ stop further processing
        }

        /* ===== RESERVATION LIST / SEARCH ===== */
        String search = req.getParameter("search");
        String month = req.getParameter("month");

        List<Reservation> list;

        if (search != null && !search.isEmpty()) {
            list = reservationService.search(search);

        } else if (month != null && !month.isEmpty()) {
            String[] parts = month.split("-");
            list = reservationService.getMonthly(
                    Integer.parseInt(parts[0]),
                    Integer.parseInt(parts[1])
            );

        } else {
            list = reservationService.getAll();
        }

        req.setAttribute("reservations", list);


        /* ===== BREADCRUMB ===== */
        req.setAttribute("breadcrumbs",
                new String[]{"Reservations", "Reservation List"});

        req.setAttribute("breadcrumbLinks",
                new String[]{
                        req.getContextPath() + "/reservation",
                        null
                });

        req.getRequestDispatcher("reservation-list.jsp")
                .forward(req, resp);

    }

    /* =========================
       POST: SAVE RESERVATION + BILL
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
            /* ===== GUEST ===== */
            Guest guest = new Guest(
                    request.getParameter("name"),
                    request.getParameter("address"),
                    request.getParameter("email"),
                    request.getParameter("phone")
            );

            /* ===== RESERVATION ===== */
            int roomId =
                    Integer.parseInt(request.getParameter("roomId"));

            LocalDate checkIn =
                    LocalDate.parse(request.getParameter("checkIn"));
            LocalDate checkOut =
                    LocalDate.parse(request.getParameter("checkOut"));

            long nights =
                    ChronoUnit.DAYS.between(checkIn, checkOut);

            if (nights <= 0) {
                throw new IllegalStateException(
                        "Check-out date must be after check-in date");
            }

            Reservation reservation =
                    new Reservation.Builder()
                            .roomId(roomId)
                            .checkIn(checkIn)
                            .checkOut(checkOut)
                            .createdBy(userId)
                            .build();

            /* ===== SAVE RESERVATION ===== */
            int reservationId =
                    reservationService.makeReservation(guest, reservation);

            /* ===== BILLING ===== */
            BigDecimal roomRate =
                    reservationService.getRoomRate(roomId);

            Bill bill =
                    new Bill.Builder()
                            .reservationId(reservationId)
                            .nights((int) nights)
                            .roomRate(roomRate)
                            .paymentMethod(request.getParameter("paymentMethod"))
                            .createdBy(userId)
                            .build();

            billingService.generateBill(bill);

            request.setAttribute("bill", bill);
            request.getRequestDispatcher("bill-print.jsp")
                    .forward(request, response);

        } catch (Exception e) {

            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("reservation.jsp")
                    .forward(request, response);
        }

        /* ===== BREADCRUMB (IMPORTANT) ===== */
        request.setAttribute("breadcrumbs",
                new String[]{"Dashboard", "Reservation List"});

        request.setAttribute("breadcrumbLinks",
                new String[]{
                        request.getContextPath() + "/dashboard",
                        null // current page
                });

        /* ===== FORWARD (LAST LINE ONLY) ===== */
        request.getRequestDispatcher("reservation-list.jsp")
                .forward(request, response);


    }
}
