package com.mashreq.roombooking.db.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.*;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

/**
 * Room entity class
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
@Table(name = "rooms")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "room_name",
            nullable = false)
    @NotNull(message = "Room Name can not be null!")
    private String roomName;

    @Column(name = "capacity",
            nullable = false)
    @NotNull(message = "Capacity can not be null!")
    @Min(value = 1, message = "Capacity should be more than 0 value.")
    private int capacity ;

    @Column(name = "available_for_booking",
            nullable = false)
    private boolean availableForBooking = true;

}
