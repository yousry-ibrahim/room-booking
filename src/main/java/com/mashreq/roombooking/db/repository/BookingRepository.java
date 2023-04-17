package com.mashreq.roombooking.db.repository;

import com.mashreq.roombooking.db.model.Booking;
import com.mashreq.roombooking.db.model.Room;
import com.mashreq.roombooking.dto.BookingRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface BookingRepository extends CrudRepository<Booking, Long> {

    @Query("SELECT cr FROM Room cr "
            + "WHERE cr.id NOT IN "
            + "(SELECT b.conferenceRoom.id FROM Booking b "
            + "WHERE (b.startTime >= :startTime AND b.startTime < :endTime) "
            + "OR (b.endTime > :startTime AND b.endTime <= :endTime))")
    List<Room> findAvailableRooms(LocalTime startTime, LocalTime endTime);


    @Query("SELECT cr FROM Room cr "
            + "WHERE cr.capacity >= :numAttendees "
            + "AND cr.id NOT IN "
            + "(SELECT b.conferenceRoom.id FROM Booking b "
            + "WHERE (b.startTime >= :startTime AND b.startTime < :endTime) "
            + "OR (b.endTime > :startTime AND b.endTime <= :endTime)) "
            + "ORDER BY cr.capacity ASC")
    List<Room> findSuitableRooms(LocalTime startTime, LocalTime endTime, int numAttendees);



    default Optional<Booking> findSuitableRoomForBookingAndBook(BookingRequest bookingRequest){
        List<Room> suitableRooms = findSuitableRooms(
                bookingRequest.getStartTime(),
                bookingRequest.getEndTime(),
                bookingRequest.getNumAttendees()
        );
        if (suitableRooms.isEmpty()) {
            Optional.empty();
        }

        // Book the first suitable conference room
        Booking booking = Booking.builder().conferenceRoom(suitableRooms.get(0))
                .numAttendees(bookingRequest.getNumAttendees())
                        .startTime(bookingRequest.getStartTime())
                                .endTime(bookingRequest.getEndTime()).build();
        booking  = save(booking);
        return Optional.of(save(booking));
    }
}
