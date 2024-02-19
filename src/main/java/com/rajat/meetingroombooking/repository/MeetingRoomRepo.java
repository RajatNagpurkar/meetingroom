package com.rajat.meetingroombooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rajat.meetingroombooking.entity.MeetingRoom;

public interface MeetingRoomRepo extends JpaRepository<MeetingRoom, Long>{

}
