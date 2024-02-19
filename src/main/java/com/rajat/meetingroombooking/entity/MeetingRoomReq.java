package com.rajat.meetingroombooking.entity;

import java.io.Serializable;

public class MeetingRoomReq implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private RoomType roomType;
	
	public MeetingRoomReq(){
		
	}

	public MeetingRoomReq(Long id, RoomType roomType) {
		super();
		this.id = id;
		this.roomType = roomType;
	}

	@Override
	public String toString() {
		return "MeetingRoom [id=" + id + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public RoomType getRoomType() {
		return roomType;
	}

	public void setRoomType(RoomType roomType) {
		this.roomType = roomType;
	}

}
