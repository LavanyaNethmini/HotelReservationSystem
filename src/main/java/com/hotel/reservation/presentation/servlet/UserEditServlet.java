package com.hotel.reservation.presentation.servlet;

import com.hotel.reservation.domain.model.User;
import com.hotel.reservation.service.UserService;
import com.hotel.reservation.service.impl.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/user-edit")
public class UserEditServlet extends HttpServlet {

    private final UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        String role = (String) request.getSession().getAttribute("role");

        // 🔐 ADMIN protection (same as reset)
        if (role == null || !role.equals("ADMIN")) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        int id = Integer.parseInt(request.getParameter("id"));
        User user = userService.findById(id);

        request.setAttribute("user", user);
        request.getRequestDispatcher("/user-edit.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("userId"));

        // 1️⃣ Get existing user first
        User existingUser = userService.findById(id);

        String newUsername = request.getParameter("username");

        if (!existingUser.getUsername().equals(newUsername) &&
                userService.usernameExists(newUsername)) {

            request.setAttribute("error", "Username already exists.");
            request.setAttribute("user", existingUser);
            request.getRequestDispatcher("/user-edit.jsp").forward(request, response);
            return;
        }



        // 2️⃣ Rebuild with updated fields
        User updatedUser = new User.UserBuilder()
                .userId(existingUser.getUserId())
                .username(request.getParameter("username"))
                .password(existingUser.getPassword())   // keep same
                .fullName(request.getParameter("fullName"))
                .contactNo(request.getParameter("contactNo"))
                .address(request.getParameter("address"))
                .role(request.getParameter("role"))
                .build();


        System.out.println("New username: " + request.getParameter("username"));

        // 3️⃣ Update
        userService.updateUser(updatedUser);

        response.sendRedirect(request.getContextPath() + "/users");
    }
}