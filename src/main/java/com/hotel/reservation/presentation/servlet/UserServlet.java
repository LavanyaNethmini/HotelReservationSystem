package com.hotel.reservation.presentation.servlet;

import com.hotel.reservation.domain.model.User;
import com.hotel.reservation.service.UserService;
import com.hotel.reservation.service.impl.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class UserServlet extends HttpServlet {

    private final UserService userService = new UserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            // 1. Read form parameters
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String fullName = request.getParameter("fullName");
            String contactNo = request.getParameter("contactNo");
            String address = request.getParameter("address");
            String role = request.getParameter("role");

            // 2. Build User domain object (Builder Pattern)
            User user = User.builder()
                    .username(username)
                    .password(password)
                    .fullName(fullName)
                    .contactNo(contactNo)
                    .address(address)
                    .role(role)
                    .build();

            // 3. Call Service Layer (Facade)
            userService.registerUser(user);

            // 4. Forward success
            request.setAttribute("message", "User registered successfully");
            request.getRequestDispatcher("/WEB-INF/views/user-success.jsp")
                    .forward(request, response);

        } catch (Exception e) {
            // 5. Handle error
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/WEB-INF/views/user-error.jsp")
                    .forward(request, response);
        }
    }
}
