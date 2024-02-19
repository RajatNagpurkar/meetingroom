package com.rajat.meetingroombooking.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "meeting_room")
public class MeetingRoom {
	   
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	    
	@Enumerated(EnumType.STRING)
	private RoomType roomType;
	
	public MeetingRoom() {
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

	public MeetingRoom(Long id, RoomType roomType) {
		super();
		this.id = id;
		this.roomType = roomType;
	}

	@Override
	public String toString() {
		return "MeetingRoom [id=" + id + "]";
	}
}
