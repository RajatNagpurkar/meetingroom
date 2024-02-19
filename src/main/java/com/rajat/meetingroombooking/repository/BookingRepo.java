package com.rajat.meetingroombooking.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.rajat.meetingroombooking.entity.Booking;

public interface BookingRepo extends JpaRepository<Booking, Long> {

//	 List<Booking> findByStartTimeAfterAndStartTimeBefore(LocalDateTime start, LocalDateTime end);

	@Query("SELECT b FROM Booking b WHERE ((b.startTime BETWEEN :startTime AND :endTime) OR (b.endTime BETWEEN :startTime AND :endTime)) AND b.meetingRoom.id = :meetingRoomId")
	List<Booking> findConflictingBookings(@Param("startTime") LocalDateTime startTime,@Param("endTime") LocalDateTime endTime,@Param("meetingRoomId") Long meetingRoomId);
	
//	@Query("SELECT b FROM Booking b WHERE b.employeeId = :employeeId")
//    List<Booking> findBookingsByEmployeeId(@Param("employeeId") Long employeeId);

}
