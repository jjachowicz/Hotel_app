package com.example.demo.Controllers;

import com.example.demo.Entities.GuestEntity;
import com.example.demo.Entities.ReservationEntity;
import com.example.demo.Entities.RoomEntity;
import com.example.demo.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.transaction.Transactional;
import java.awt.*;
import java.util.List;

@Controller
public class RoomController {

    @Autowired
    private RoomRepository roomRepository;

    // list all rooms
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/api/rooms",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Transactional
    public ResponseEntity<List<RoomEntity>> getRooms() {
        return ResponseEntity.ok(this.roomRepository.findAll());
    }

    // list free rooms
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/api/freerooms",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Transactional
    public ResponseEntity<List<RoomEntity>> freeRooms() {
        return ResponseEntity.ok(this.roomRepository.findFreeRoomEntity());
    }

    // list room by number
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/api/rooms/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Transactional
    public ResponseEntity<RoomEntity> getRoom(@PathVariable("id") Long roomId ) {
        return ResponseEntity.of(this.roomRepository.findById(roomId));
    }

    // find rooms which are bigger than...
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/api/biggerrooms",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Transactional
    public ResponseEntity<List<RoomEntity>> getBiggerRooms() {
        return ResponseEntity.ok(this.roomRepository.findRoomsWhereSizeMoreThan());
    }


    ////////
    // find most visited rooms
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/api/mostvisitedroom",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Transactional
    public ResponseEntity<List<RoomEntity>> getFavouriteRoom() {
        return ResponseEntity.ok(this.roomRepository.findMostVisitedRoom());
    }


}
