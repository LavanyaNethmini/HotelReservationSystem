package com.hotel.reservation.presentation.servlet;

import com.hotel.reservation.domain.model.User;
import com.hotel.reservation.service.UserService;
import com.hotel.reservation.service.impl.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/users")
public class UserManagementServlet extends HttpServlet {

    private final UserService userService =
            new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);

        if (session == null ||
                !"ADMIN".equals(session.getAttribute("role"))) {
            resp.sendRedirect("unauthorized.jsp");
            return;
        }

        List<User> users = userService.getAllUsers();

        System.out.println("Users loaded: " + users.size());

        req.setAttribute("users", users);

        req.getRequestDispatcher("/user-list.jsp")
                .forward(req, resp);
    }
}
