package com.rajat.meetingroombooking.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.rajat.meetingroombooking.controller.BookingController;
import com.rajat.meetingroombooking.entity.Booking;
import com.rajat.meetingroombooking.entity.BookingReq;

public interface BookingRepo extends JpaRepository<Booking, Long> {

	@Query("SELECT b FROM Booking b WHERE ((b.startTime BETWEEN :startTime AND :endTime) OR (b.endTime BETWEEN :startTime AND :endTime)) AND b.meetingRoom.id = :meetingRoomId")
	List<Booking> findConflictingBookings(@Param("startTime") LocalDateTime startTime,
			@Param("endTime") LocalDateTime endTime, @Param("meetingRoomId") Long meetingRoomId);

	Optional<Booking> findById(Long bookingId);
	
	List<Booking> findByEmployeeIdAndStartTimeAfter(String employeeId, LocalDateTime startTime);


//	List<Booking> findByEmployeeId(Long employeeId);

//	@Query("SELECT b FROM Booking b WHERE b.employeeId = :employeeId")
//    List<Booking> findBookingsByEmployeeId(@Param("employeeId") Long employeeId);

}
