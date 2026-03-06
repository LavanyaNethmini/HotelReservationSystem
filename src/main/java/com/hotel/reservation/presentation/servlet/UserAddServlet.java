package com.hotel.reservation.presentation.servlet;

import com.hotel.reservation.domain.model.User;
import com.hotel.reservation.service.UserService;
import com.hotel.reservation.service.impl.UserServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/user-add")
public class UserAddServlet extends HttpServlet {

    private final UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp)
            throws ServletException, IOException {

        // 🔐 Role check
        String role = (String) req.getSession().getAttribute("role");

        if (role == null || !role.equals("ADMIN")) {
            resp.sendRedirect(req.getContextPath() + "/dashboard");
            return;
        }

        req.setAttribute("breadcrumbs",
                new String[]{"Dashboard", "User Management", "Add User"});
        req.setAttribute("breadcrumbLinks",
                new String[]{
                        req.getContextPath() + "/dashboard",
                        req.getContextPath() + "/users",
                        null
                });

        req.getRequestDispatcher("/user-add.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req,
                          HttpServletResponse resp)
            throws ServletException, IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String fullName = req.getParameter("fullName");
        String contactNo = req.getParameter("contactNo");
        String address = req.getParameter("address");
        String role = req.getParameter("role");

        // 🔥 Using Builder Pattern
        User user = User.builder()
                .username(username)
                .password(password)
                .fullName(fullName)
                .contactNo(contactNo)
                .address(address)
                .role(role)
                .build();
        try {

            userService.save(user);

            req.getSession().setAttribute("success", "User added successfully!");
            resp.sendRedirect(req.getContextPath() + "/users");

        } catch (Exception e) {

            Throwable cause = e.getCause();

            if (cause != null && cause.getMessage().contains("Duplicate entry")) {
                req.setAttribute("error", "Username already exists. Please choose another username.");
            } else {
                req.setAttribute("error", "Error saving user.");
            }

            req.getRequestDispatcher("user-add.jsp").forward(req, resp);
        }
    }
    }
