package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.transaction.Transactional;
import java.util.List;

@Controller
public class GuestController {

    @Autowired
    private GuestRepository guestRepository;

    // get list of guests
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/api/guests",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Transactional
    public ResponseEntity<List<GuestEntity>> getGuests() {
        return ResponseEntity.ok(this.guestRepository.findAll());
    }

    // get separate guest
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/api/guests/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Transactional
    public ResponseEntity<GuestEntity> getGuest(@PathVariable("id") Long guestId ) {
        return ResponseEntity.of(this.guestRepository.findById(guestId));
    }

    // create new guest
    //echo '{"name":"john", "surname":"smith", "email":"john@email.com", "pesel":"123456789", "phoneNumber":"+48 123 456 789"}' | curl -X POST -H "Content-Type: application/json" -d @- http://localhost:8080/api/guests/create
    @RequestMapping(
            method = RequestMethod.POST,
            value = "/api/guests/create",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Transactional
    public ResponseEntity<GuestEntity> createGuest(@RequestBody GuestEntity guestEntity) {
        return ResponseEntity.ok(this.guestRepository.save(guestEntity));
    }

    // edit user



}
