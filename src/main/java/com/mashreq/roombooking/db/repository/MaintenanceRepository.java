package com.mashreq.roombooking.db.repository;

import com.mashreq.roombooking.db.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalTime;

public interface MaintenanceRepository extends JpaRepository<Booking, Long> {

    @Query("SELECT COUNT(*) FROM MaintenanceSchedule m "
            + "WHERE ((m.startTime >= :startTime AND m.startTime < :endTime) "
            + "OR (m.endTime > :startTime AND m.endTime <= :endTime))")
    int countMaintenanceSchedules(LocalTime startTime, LocalTime endTime);
}
