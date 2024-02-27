package com.rajat.meetingroombooking.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import com.rajat.meetingroombooking.entity.Booking;

public interface BookingService {

	List<Booking> getAllBookings();

	List<Booking> getUpcomingBookingsByEmployeeId(Long employeeId, LocalDateTime currentTime);

//	List<Booking> getBookingsByEmployeeId(Long employeeId);

}
