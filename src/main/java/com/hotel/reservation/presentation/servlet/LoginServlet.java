package com.hotel.reservation.presentation.servlet;

import com.hotel.reservation.domain.model.User;
import com.hotel.reservation.service.UserService;
import com.hotel.reservation.service.impl.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

import java.io.IOException;

public class LoginServlet extends HttpServlet {

    private final UserService userService = new UserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = userService.login(username, password);

        if (user != null) {

            HttpSession session = request.getSession();
            session.setAttribute("userId", user.getUserId());
            session.setAttribute("username", user.getUsername());
            session.setAttribute("role", user.getRole());

            response.sendRedirect("dashboard.jsp");
        } else {
            request.setAttribute("error", "Invalid username or password");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

}
