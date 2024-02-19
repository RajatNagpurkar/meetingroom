package com.rajat.meetingroombooking.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rajat.meetingroombooking.entity.Booking;
import com.rajat.meetingroombooking.entity.BookingReq;
import com.rajat.meetingroombooking.service.BookingService;

@RestController
@RequestMapping("/bookings")
public class BookingController {
	
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
	
	@Autowired
	private BookingService bookingService;

	@PostMapping("/book")
	public ResponseEntity<String> bookRoom(@RequestBody BookingReq bookingReq) {
		if (!bookingService.isBookingValid(bookingReq)) {
			return ResponseEntity.badRequest().body("Invalid booking request");
		}
		
		LocalDateTime startTime = LocalDateTime.parse(bookingReq.getStartTime(), formatter);
		LocalDateTime endTime = LocalDateTime.parse(bookingReq.getEndTime(), formatter);
		
		Optional<Booking> conflictingBooking = bookingService.findConflictingBooking(startTime, endTime, bookingReq.getMeetingRoom().getId());
		if (conflictingBooking.isPresent()) {
			return new ResponseEntity<>("Meeting room is already booked for this time slot.", HttpStatus.BAD_REQUEST);
		}else {
			bookingService.saveBooking(bookingReq);
			return ResponseEntity.ok("Meeting Room Booked Successfully");
		}
	}
}
