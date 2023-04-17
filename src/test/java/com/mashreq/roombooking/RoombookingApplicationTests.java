package com.mashreq.roombooking;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.mashreq.roombooking.dto.BookingRequest;
import com.mashreq.roombooking.service.BookingService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalTime;

import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class RoombookingApplicationTests {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private BookingService bookingService;

	@Autowired
	ObjectMapper mapper;

	@Test
	public void testGetAllAvailableBookings() throws Exception {

		mvc.perform(get("/v1/bookings/available")
						.queryParam("startTime","10:00:00")
						.queryParam("endTime", "10:30:00")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}


	@Test
	public void testRoomBookingAndNumberOfAvailableRooms() throws Exception {

		LocalTime start = LocalTime.of(10, 00, 00);
		LocalTime end = LocalTime.of(10, 30, 00);

		int numberBeforeBooking = bookingService.getAvailableRooms(start,end).size();

		// booking at the same time
		BookingRequest request = BookingRequest.builder()
						.startTime(start)
								.endTime(end)
										.numAttendees(6).build();

		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();

		mvc.perform(MockMvcRequestBuilders.post("/v1/bookings")
						.contentType(MediaType.APPLICATION_JSON)
						.content(ow.writeValueAsString(request)))
				.andExpect(status().isCreated());

		// After Booking

		int numberAfterBooking = bookingService.getAvailableRooms(start,end).size();

		assertTrue(numberBeforeBooking - numberAfterBooking == 1);
	}

}
