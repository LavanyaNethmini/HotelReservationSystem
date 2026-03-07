package com.hotel.reservation.presentation.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.*;

import java.io.IOException;

public class AuthorizationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        HttpSession session = req.getSession(false);
        String uri = req.getRequestURI();

        // Allow login page
        if (uri.contains("login.jsp") || uri.contains("/login")) {
            chain.doFilter(request, response);
            return;
        }

        // Check login
        if (session == null || session.getAttribute("role") == null) {
            res.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }

        String role = session.getAttribute("role").toString();



        // Admin-only pages
        if (
                (uri.contains("/user-add") ||
                        uri.contains("/user-edit") ||
                        uri.contains("/user-reset-password") ||
                        uri.contains("/generate-report") ||
                        uri.contains("/room-add") ||
                        uri.contains("/reports"))
                        && !role.equals("ADMIN")
        ) {
            res.sendRedirect(req.getContextPath() + "/unauthorized.jsp");
            return;
        }

        chain.doFilter(request, response);
    }
}