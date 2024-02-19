package com.rajat.meetingroombooking.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "booking")
public class Booking {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "room_id")
	private MeetingRoom meetingRoom;

	private LocalDateTime startTime;

	private LocalDateTime endTime;

	private String employeeId;

	private String department;

	private String purposeOfBooking;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public MeetingRoom getMeetingRoom() {
		return meetingRoom;
	}

	public void setMeetingRoom(MeetingRoom meetingRoom) {
		this.meetingRoom = meetingRoom;
	}

	public LocalDateTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}

	public LocalDateTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalDateTime endTime) {
		this.endTime = endTime;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getPurposeOfBooking() {
		return purposeOfBooking;
	}

	public void setPurposeOfBooking(String purposeOfBooking) {
		this.purposeOfBooking = purposeOfBooking;
	}

	public Booking(Long id, MeetingRoom meetingRoom, LocalDateTime startTime, LocalDateTime endTime, String employeeId,
			String department, String purposeOfBooking) {
		super();
		this.id = id;
		this.meetingRoom = meetingRoom;
		this.startTime = startTime;
		this.endTime = endTime;
		this.employeeId = employeeId;
		this.department = department;
		this.purposeOfBooking = purposeOfBooking;
	}

	public Booking() {

	}

	@Override
	public String toString() {
		return "Booking [id=" + id + ", meetingRoom=" + meetingRoom + ", startTime=" + startTime + ", endTime="
				+ endTime + ", employeeId=" + employeeId + ", department=" + department + ", purposeOfBooking="
				+ purposeOfBooking + "]";
	}	
}