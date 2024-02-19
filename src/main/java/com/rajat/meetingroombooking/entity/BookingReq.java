package com.rajat.meetingroombooking.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

public class BookingReq implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long id;

	private MeetingRoomReq meetingRoom;

	private String startTime;

	private String endTime;

	private String employeeId;

	private String department;

	private String purposeOfBooking;
	
	public BookingReq() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public MeetingRoomReq getMeetingRoom() {
		return meetingRoom;
	}

	public void setMeetingRoom(MeetingRoomReq meetingRoom) {
		this.meetingRoom = meetingRoom;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
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

	@Override
	public String toString() {
		return "BookingReq [id=" + id + ", meetingRoom=" + meetingRoom + ", startTime=" + startTime + ", endTime="
				+ endTime + ", employeeId=" + employeeId + ", department=" + department + ", purposeOfBooking="
				+ purposeOfBooking + "]";
	}
	
	

}