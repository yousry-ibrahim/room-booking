package com.mashreq.roombooking.service;

import com.mashreq.roombooking.db.repository.RoomRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;


}
