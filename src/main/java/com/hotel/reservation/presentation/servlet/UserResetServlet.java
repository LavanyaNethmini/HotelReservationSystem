package com.hotel.reservation.presentation.servlet;

import com.hotel.reservation.service.UserService;
import com.hotel.reservation.service.impl.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/user-reset")
public class UserResetServlet extends HttpServlet {

    private final UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp)
            throws ServletException, IOException {

        String role = (String) req.getSession().getAttribute("role");

        if (role == null || !role.equals("ADMIN")) {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        int id = Integer.parseInt(req.getParameter("id"));

        req.setAttribute("userId", id);
        req.getRequestDispatcher("/user-reset.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req,
                          HttpServletResponse resp)
            throws ServletException, IOException {

        String role = (String) req.getSession().getAttribute("role");

        if (role == null || !role.equals("ADMIN")) {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        int id = Integer.parseInt(req.getParameter("userId"));
        String newPassword = req.getParameter("password");

        userService.resetPassword(id, newPassword);

        resp.sendRedirect(req.getContextPath() + "/users");
    }
}

