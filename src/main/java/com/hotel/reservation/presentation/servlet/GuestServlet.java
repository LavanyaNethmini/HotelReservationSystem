package com.hotel.reservation.presentation.servlet;

import com.hotel.reservation.domain.model.Guest;
import com.hotel.reservation.service.GuestService;
import com.hotel.reservation.service.impl.GuestServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class GuestServlet extends HttpServlet {

    private final GuestService guestService =
            new GuestServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp)
            throws ServletException, IOException {

        String search = req.getParameter("search");

        List<Guest> guests;

        if (search != null && !search.trim().isEmpty()) {
            guests = guestService.search(search);
        } else {
            guests = guestService.getAll();
        }

        System.out.println("Guests size: " + guests.size());


        req.setAttribute("guests", guests);
        req.getRequestDispatcher("guest-list.jsp")
                .forward(req, resp);
    }
}
