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

        // Not logged in
        if (session == null || session.getAttribute("role") == null) {
            res.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }

        String role = session.getAttribute("role").toString();
        String uri = req.getRequestURI();

        // ===== ADMIN ONLY =====
        if (uri.contains("/admin") && !role.equals("ADMIN")) {
            res.sendRedirect(req.getContextPath() + "/unauthorized.jsp");
            return;
        }

        // ===== STAFF ONLY =====
        if (uri.contains("/staff") && role.equals("ADMIN")) {
            // Admin can access staff pages
            chain.doFilter(request, response);
            return;
        }

        chain.doFilter(request, response);
    }
}
