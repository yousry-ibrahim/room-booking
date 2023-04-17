package com.mashreq.roombooking.service;

import com.mashreq.roombooking.db.model.Booking;
import com.mashreq.roombooking.db.model.Room;
import com.mashreq.roombooking.db.repository.BookingRepository;
import com.mashreq.roombooking.db.repository.MaintenanceRepository;
import com.mashreq.roombooking.dto.BookingRequest;
import com.mashreq.roombooking.dto.BookingResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;
    private final MaintenanceRepository maintenanceRepository;


    public List<Room> getAvailableRooms(LocalTime start, LocalTime end){
        return bookingRepository.findAvailableRooms(start,end);
    }

    public BookingResponse bookRoom(BookingRequest booking){

        if(maintenanceRepository.countMaintenanceSchedules(booking.getStartTime(),booking.getEndTime()) > 0){ // there is a maintenance
            return BookingResponse.builder()
                    .message("There is a Maintenance for the Rooms on the time you select, You can not book any room on this time.")
                    .build();
        }

        Optional<Booking> savedBooking = bookingRepository.findSuitableRoomForBookingAndBook(booking);

        if (savedBooking.isPresent()){
            return BookingResponse.builder()
                    .bookingId(savedBooking.get().getId())
                    .message("Booking Crated Successfully.")
                    .build();
        }else {
            return BookingResponse.builder()
                    .message("There is not available room in your selected time")
                    .build();
        }
    }

    public Optional<Booking> getBookingById(long id){
        Optional<Booking> booking = bookingRepository.findById(id);
        if (booking.isPresent()){
            return booking;
        }else {
            throw new EntityNotFoundException();
        }
    }
}
