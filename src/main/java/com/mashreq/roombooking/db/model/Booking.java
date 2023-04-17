package com.mashreq.roombooking.db.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.*;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.time.LocalTime;

/**
 * Booking entity class
 * @author : yousry
 * @version : 1.0
 */
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString
@Table(name = "Bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "conference_room_id")
    private Room conferenceRoom;

    @Column(name = "start_time")
    @NotNull(message = "start_time can not be null!")
    private LocalTime startTime;

    @Column(name = "end_time")
    @NotNull(message = "end_time can not be null!")
    private LocalTime  endTime;

    @Column(name = "num_attendees")
    @Min(value = 1, message = "Attendees should be more than 0 value.")
    private int numAttendees;
}
