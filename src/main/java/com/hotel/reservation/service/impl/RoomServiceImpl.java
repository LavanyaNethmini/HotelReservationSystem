package com.hotel.reservation.service.impl;

import com.hotel.reservation.domain.model.Room;
import com.hotel.reservation.dto.RoomAvailabilityDTO;
import com.hotel.reservation.repository.RoomRepository;
import com.hotel.reservation.repository.impl.RoomRepositoryImpl;
import com.hotel.reservation.service.RoomService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository =
            new RoomRepositoryImpl();

    @Override
    public List<RoomAvailabilityDTO> getRoomsWithAvailability(
            LocalDate checkIn, LocalDate checkOut) {

        List<Room> rooms = roomRepository.findAll();
        List<Integer> bookedRoomIds =
                roomRepository.findBookedRoomIds(checkIn, checkOut);

        List<RoomAvailabilityDTO> result = new ArrayList<>();

        for (Room r : rooms) {
            boolean available = !bookedRoomIds.contains(r.getRoomId());
            result.add(new RoomAvailabilityDTO(r, available));
        }

        return result;
    }
}
