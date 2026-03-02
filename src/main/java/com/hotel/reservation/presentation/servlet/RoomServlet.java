package com.hotel.reservation.presentation.servlet;

import com.hotel.reservation.dto.RoomAvailabilityDTO;
import com.hotel.reservation.service.RoomService;
import com.hotel.reservation.service.impl.RoomServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/rooms")
public class RoomServlet extends HttpServlet {

    private final RoomService roomService =
            new RoomServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp)
            throws ServletException, IOException {

        String checkInStr = req.getParameter("checkIn");
        String checkOutStr = req.getParameter("checkOut");

        List<RoomAvailabilityDTO> rooms;

        if (checkInStr != null && checkOutStr != null) {
            LocalDate checkIn = LocalDate.parse(checkInStr);
            LocalDate checkOut = LocalDate.parse(checkOutStr);

            rooms = roomService.getRoomsWithAvailability(checkIn, checkOut);
        } else {
            rooms = roomService.getRoomsWithAvailability(
                    LocalDate.now(), LocalDate.now().plusDays(1)
            );
        }
        System.out.println("Rooms loaded: " + rooms.size());

        req.setAttribute("rooms", rooms);

        req.setAttribute("breadcrumbs",
                new String[]{"Dashboard", "Rooms"});
        req.setAttribute("breadcrumbLinks",
                new String[]{
                        req.getContextPath() + "/dashboard",
                        null
                });

        req.getRequestDispatcher("/rooms.jsp")
                .forward(req, resp);
    }
}

