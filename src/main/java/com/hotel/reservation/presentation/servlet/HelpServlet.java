package com.hotel.reservation.presentation.servlet;

import com.hotel.reservation.help.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

import java.io.IOException;

public class HelpServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {



        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("role") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String role = (String) session.getAttribute("role");

        HelpContext context = new HelpContext();

        if ("ADMIN".equalsIgnoreCase(role)) {
            context.setStrategy(new AdminHelpStrategy());
        } else {
            context.setStrategy(new StaffHelpStrategy());
        }

        request.setAttribute("helpContent", context.showHelp());
        request.getRequestDispatcher("help.jsp")
                .forward(request, response);

        request.setAttribute("breadcrumbs",
                new String[]{"Help Guide"});

        request.setAttribute("breadcrumbLinks",
                new String[]{ null });

    }
}
