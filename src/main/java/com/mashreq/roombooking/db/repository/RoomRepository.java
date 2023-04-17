package com.mashreq.roombooking.db.repository;

import com.mashreq.roombooking.db.model.Booking;
import com.mashreq.roombooking.db.model.Room;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalTime;
import java.util.List;

public interface RoomRepository extends CrudRepository<Booking, Long> {
}


