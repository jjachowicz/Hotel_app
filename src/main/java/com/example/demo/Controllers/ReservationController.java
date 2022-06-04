package com.example.demo.Controllers;

import com.example.demo.Entities.GuestEntity;
import com.example.demo.Entities.ReservationEntity;
import com.example.demo.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.transaction.Transactional;
import java.util.List;

@Controller
public class ReservationController {

    @Autowired
    private ReservationRepository reservationRepository;

    // get list of reservations
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/api/reservations",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Transactional
    public ResponseEntity<List<ReservationEntity>> getReservations() {
        return ResponseEntity.ok(this.reservationRepository.findAll());
    }
}
