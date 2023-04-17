package com.mashreq.roombooking.controller;

import com.mashreq.roombooking.db.model.Booking;
import com.mashreq.roombooking.db.model.Room;
import com.mashreq.roombooking.dto.BookingRequest;
import com.mashreq.roombooking.dto.BookingResponse;
import com.mashreq.roombooking.service.BookingService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/v1/bookings")
@AllArgsConstructor
@CrossOrigin
public class BookingController {

    private BookingService bookingService;

    @GetMapping("/available")
    @Operation(summary = "find Available Rooms")
    public List<Room> findAvailableRooms(@NotNull @RequestParam LocalTime startTime, @NotNull @RequestParam LocalTime endTime){
        return bookingService.getAvailableRooms(startTime,endTime);
    }


    @PostMapping
    @Operation(summary = "Booking a Room.")
    @ResponseStatus(HttpStatus.CREATED)
    public BookingResponse bookRoom(@Valid @RequestBody BookingRequest request){
        return bookingService.bookRoom(request);
    }

    @GetMapping("/{id}")
    @ResponseBody
    Booking get(@PathVariable long id) throws EntityNotFoundException {
        return bookingService.getBookingById(id).get();
    }
}
