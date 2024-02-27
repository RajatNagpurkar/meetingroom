package com.rajat.meetingroombooking.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rajat.meetingroombooking.entity.Booking;
import com.rajat.meetingroombooking.entity.BookingReq;
import com.rajat.meetingroombooking.service.impl.BookingServiceImpl;

@RestController
@RequestMapping("/bookings")
public class BookingController {

	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
	@Autowired
	private BookingServiceImpl bookingService;

	@GetMapping
	public ResponseEntity<List<Booking>> getAllBookings() {
		List<Booking> bookings = bookingService.getAllBookings();
		return ResponseEntity.ok(bookings);
	}

	@GetMapping("/{employeeId}")
	public ResponseEntity<List<Booking>> getUpcomingBookingsByEmployeeId(@PathVariable String employeeId) {
		List<Booking> bookings = bookingService.getUpcomingBookingsByEmployeeId(employeeId);
		return ResponseEntity.ok(bookings);
	}

	@PostMapping("/book")
	public ResponseEntity<String> bookRoom(@RequestBody BookingReq bookingReq) {
		if (!bookingService.isBookingValid(bookingReq)) {
			return ResponseEntity.badRequest().body("Invalid booking request");
		}
		LocalDateTime startTime = LocalDateTime.parse(bookingReq.getStartTime(), formatter);
		LocalDateTime endTime = LocalDateTime.parse(bookingReq.getEndTime(), formatter);

		Optional<Booking> conflictingBooking = bookingService.findConflictingBooking(startTime, endTime,
				bookingReq.getMeetingRoom().getId());
		if (conflictingBooking.isPresent()) {
			return new ResponseEntity<>("Meeting room is already booked for this time slot.", HttpStatus.BAD_REQUEST);
		} else {
			bookingService.saveBooking(bookingReq);
			return ResponseEntity.ok("Meeting Room Booked Successfully");
		}
	}

	@PutMapping("/{bookingId}")
	public ResponseEntity<String> updateBooking(@PathVariable Long bookingId, @RequestBody BookingReq updateRequest) {

		boolean updated = bookingService.updateBooking(bookingId, updateRequest);
		if (updated) {
			return ResponseEntity.ok("Booking updated successfully");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Booking not found");
		}
	}

	@DeleteMapping("/{bookingId}")
	public ResponseEntity<String> cancelBooking(@PathVariable Long bookingId) {

		boolean cancelled = bookingService.cancelBooking(bookingId);
		if (cancelled) {
			return ResponseEntity.ok("Booking cancelled successfully");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Booking not found");
		}
	}
}
