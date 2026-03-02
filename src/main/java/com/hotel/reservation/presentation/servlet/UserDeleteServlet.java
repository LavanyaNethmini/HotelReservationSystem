package com.hotel.reservation.presentation.servlet;

import com.hotel.reservation.service.UserService;
import com.hotel.reservation.service.impl.UserServiceImpl;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import jakarta.servlet.ServletException;

import java.io.IOException;

@WebServlet("/user-delete")
public class UserDeleteServlet extends HttpServlet {

    private final UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp)
            throws ServletException, IOException {

        // 🔐 Role check (ADMIN only)
        String role = (String) req.getSession().getAttribute("role");

        if (role == null || !role.equals("ADMIN")) {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        int id = Integer.parseInt(req.getParameter("id"));

        userService.delete(id);

        req.getSession().setAttribute("success",
                "User deleted successfully");

        resp.sendRedirect(req.getContextPath() + "/users");
    }
}
