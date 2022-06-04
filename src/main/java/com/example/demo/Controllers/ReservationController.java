package com.example.demo.Controllers;

import com.example.demo.Entities.GuestEntity;
import com.example.demo.Entities.ReservationEntity;
import com.example.demo.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

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

    // add new reservation
    // echo '{"guest":{"id":1,"name":"chris","surname":"johnson1","email":"chris@email.com","pesel":"123456789","phoneNumber":"+48 123 456 789"}, "room":{"id":1,"size":30,"pricePerNight":234.0,"reserved":true}, "reservationDate":"2022-07-21"}' | curl -X POST -H "Content-Type: application/json" -d @- http://localhost:8080/api/reservations/create
    @RequestMapping(
            method = RequestMethod.POST,
            value = "/api/reservations/create",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Transactional
    public ResponseEntity<ReservationEntity> createReservation(@RequestBody ReservationEntity reservationEntity) {
        return ResponseEntity.ok(this.reservationRepository.save(reservationEntity));
    }

    // delete a reservation
    // curl -X DELETE  http://localhost:8080/api/reservations/3/remove

    @RequestMapping(
            value = "/api/reservations/{id}/remove",
            method = RequestMethod.DELETE
    )
    @ResponseBody
    public String deleteReservation(
            @PathVariable("id") Long reservationId) {
        this.reservationRepository.deleteById(reservationId);
        //return ResponseEntity.ok(this.reservationRepository.findById(reservationId));
        return " Reservation " + reservationId + " deleted.";
    }


    // edit a reservation
    // echo '{"guest":{"id":1,"name":"chris","surname":"johnson1","email":"chris@email.com","pesel":"123456789","phoneNumber":"+48 123 456 789"}, "room":{"id":1,"size":30,"pricePerNight":234.0,"reserved":true}, "reservationDate":"2022-07-26"}' | curl -X PUT -H "Content-Type: application/json" -d @- http://localhost:8080/api/reservations/edit/1
    @RequestMapping(
            method = RequestMethod.PUT,
            value = "api/reservations/edit/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Transactional
    public ResponseEntity<ReservationEntity> editReservation(
            @PathVariable("id") Long reservationId,
            @RequestBody ReservationEntity newReservationEntity
    ) {
        Optional<ReservationEntity>  foundReservationOptional = this.reservationRepository.findById(reservationId);
        if (foundReservationOptional.isPresent()) {
            ReservationEntity foundReservationEntity = foundReservationOptional.get();
            foundReservationEntity.setReservationDate(newReservationEntity.getReservationDate());
            this.reservationRepository.save(foundReservationEntity);
        }
        return ResponseEntity.of(foundReservationOptional);
    }


}
