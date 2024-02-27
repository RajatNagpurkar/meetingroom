package com.rajat.meetingroombooking.service.impl;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rajat.meetingroombooking.entity.Booking;
import com.rajat.meetingroombooking.entity.BookingReq;
import com.rajat.meetingroombooking.entity.MeetingRoom;
import com.rajat.meetingroombooking.repository.BookingRepo;

@Service
public class BookingServiceImpl {

	@Autowired
	private BookingRepo bookingRepository;

	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

	public boolean isBookingValid(BookingReq bookingReq) {

		if (bookingReq == null) {
			return false;
		}

		LocalDateTime currentTime = LocalDateTime.now();
		LocalDateTime sevenDaysAhead = currentTime.plusDays(7);

		if (bookingReq.getStartTime() == null || bookingReq.getEndTime() == null) {
			return false;
		}

		LocalDateTime startTime = LocalDateTime.parse(bookingReq.getStartTime(), formatter);
		LocalDateTime endTime = LocalDateTime.parse(bookingReq.getEndTime(), formatter);

		return startTime.isAfter(currentTime) && startTime.isBefore(sevenDaysAhead) && endTime.isAfter(startTime)
				&& Duration.between(startTime, endTime).toMinutes() >= 15
				&& Duration.between(startTime, endTime).toHours() <= 8;
	}

//	public List<Booking> getBookingsBetween(LocalDateTime start, LocalDateTime end) {
//		return bookingRepository.findByStartTimeAfterAndStartTimeBefore(start, end);
//	}

	public List<Booking> getAllBookings() {
		return bookingRepository.findAll();
	}
	
    public List<Booking> getUpcomingBookingsByEmployeeId(String employeeId) {
        return bookingRepository.findByEmployeeIdAndStartTimeAfter(employeeId, LocalDateTime.now());
    }

	public Optional<Booking> findConflictingBooking(LocalDateTime startTime, LocalDateTime endTime,
			Long meetingRoomId) {
		List<Booking> conflictingBookings = bookingRepository.findConflictingBookings(startTime, endTime,
				meetingRoomId);
		return conflictingBookings.isEmpty() ? Optional.empty() : Optional.of(conflictingBookings.get(0));
	}

	public void saveBooking(BookingReq bookingReq) {
		Booking booking = new Booking();
		BeanUtils.copyProperties(bookingReq, booking, "startTime", "endTime");
		booking.setStartTime(LocalDateTime.parse(bookingReq.getStartTime(), formatter));
		booking.setEndTime(LocalDateTime.parse(bookingReq.getEndTime(), formatter));
		MeetingRoom room = new MeetingRoom();
		BeanUtils.copyProperties(bookingReq.getMeetingRoom(), room);
		booking.setMeetingRoom(room);
		bookingRepository.save(booking);
	}

	public boolean updateBooking(Long bookingId, BookingReq updateRequest) {

		Optional<Booking> optionalBooking = bookingRepository.findById(bookingId);
		if (optionalBooking.isEmpty()) {
			return false; // Booking not found
		}

		Booking booking = optionalBooking.get();

		booking.setStartTime(LocalDateTime.parse(updateRequest.getStartTime(), formatter));
		booking.setEndTime(LocalDateTime.parse(updateRequest.getEndTime(), formatter));

		bookingRepository.save(booking);

		return true;
	}

	public boolean cancelBooking(Long bookingId) {
		Booking booking = bookingRepository.findById(bookingId).orElse(null);
		if (booking == null) {
			return false;
		}
		try {
			bookingRepository.delete(booking);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
