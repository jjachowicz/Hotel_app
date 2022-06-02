package com.example.demo.Controllers;

import com.example.demo.Entities.RoomEntity;
import com.example.demo.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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
    // to do

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

}
