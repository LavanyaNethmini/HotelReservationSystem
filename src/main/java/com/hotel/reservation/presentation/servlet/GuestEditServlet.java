package com.hotel.reservation.presentation.servlet;

import com.hotel.reservation.domain.model.Guest;
import com.hotel.reservation.service.GuestService;
import com.hotel.reservation.service.impl.GuestServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/guest-edit")
public class GuestEditServlet extends HttpServlet {

    private final GuestService guestService = new GuestServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        int guestId = Integer.parseInt(req.getParameter("id"));

        Guest guest = guestService.getById(guestId);
        req.setAttribute("guest", guest);

        req.getRequestDispatcher("/guest-edit.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Guest guest = new Guest(
                Integer.parseInt(req.getParameter("guestId")),
                req.getParameter("name"),
                req.getParameter("address"),
                req.getParameter("email"),
                req.getParameter("phone")
        );

        guestService.update(guest);

        resp.sendRedirect(req.getContextPath() + "/guests?updated=true");
    }
}
