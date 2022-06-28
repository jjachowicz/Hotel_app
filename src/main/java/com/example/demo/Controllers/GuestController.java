package com.example.demo.Controllers;

import com.example.demo.Entities.GuestEntity;
import com.example.demo.Entities.RoomEntity;
import com.example.demo.GuestPagingRepository;
import com.example.demo.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import javax.transaction.Transactional;

import org.springframework.data.domain.PageRequest;
import org.springframework.web.client.RestTemplate;


import java.util.List;
import java.util.Optional;

@Controller
public class GuestController {

    @Autowired
    private GuestRepository guestRepository;

    @Autowired
    private GuestPagingRepository guestPagingRepository;

    // get list of guests
    // localhost:8088/api/guests
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
    // localhost:8088/api/guests/{id}
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/api/guests/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Transactional
    public ResponseEntity<GuestEntity> getGuest(@PathVariable("id") Long guestId) {
        return ResponseEntity.of(this.guestRepository.findById(guestId));
    }

    // create new guest
    //echo '{"name":"mary", "surname":"smith", "email":"mary@email.com", "pesel":"123456789", "phoneNumber":"+48 123 456 789"}' | curl -X POST -H "Content-Type: application/json" -d @- http://localhost:8088/api/guests/create
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

    // edit guest
    // echo '{"name":"chris", "surname":"johnson1", "email":"chris@email.com", "pesel":"123456789", "phoneNumber":"+48 123 456 789"}' | curl -X PUT -H "Content-Type: application/json" -d @- http://localhost:8080/api/guests/edit/1
    @RequestMapping(
            method = RequestMethod.PUT,
            value = "api/guests/edit/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Transactional
    public ResponseEntity<GuestEntity> editGuest(
            @PathVariable("id") Long guestId,
            @RequestBody GuestEntity newGuestEntity
    ) {
        Optional<GuestEntity> foundGuestOptional = this.guestRepository.findById(guestId);
        if (foundGuestOptional.isPresent()) {
            GuestEntity foundGuestEntity = foundGuestOptional.get();
            foundGuestEntity.setName(newGuestEntity.getName());
            foundGuestEntity.setSurname(newGuestEntity.getSurname());
            foundGuestEntity.setEmail(newGuestEntity.getEmail());
            foundGuestEntity.setPesel(newGuestEntity.getPesel());
            foundGuestEntity.setPhoneNumber(newGuestEntity.getPhoneNumber());
            this.guestRepository.save(foundGuestEntity);
        }
        return ResponseEntity.of(foundGuestOptional);
    }

    // sort guests a-z by name
    //localhost:8088/api/guests/sortfromatoz
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/api/guests/sortfromatoz",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Transactional
    public ResponseEntity<List<GuestEntity>> sortFromAtoZ() {
        return ResponseEntity.ok(this.guestRepository.findAllByOrderByNameAsc());
    }

    // sort guests z-a by name
    //localhost:8088/api/guests/sortfromztoa
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/api/guests/sortfromztoa",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Transactional
    public ResponseEntity<List<GuestEntity>> sortFromZtoA() {
        return ResponseEntity.ok(this.guestRepository.findAllByOrderByNameDesc());
    }

    // pagination
    // localhost:8088/api/guests/pagination?page-number=0&page-size=3
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/api/guests/pagination",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Transactional
    public ResponseEntity<List<GuestEntity>> getPagination(
            @RequestParam(required = false, name="page-number", defaultValue = "1") Integer pageNumber,
            @RequestParam(required = false, name="page-size", defaultValue = "20") Integer pageSize
    ) {
        //Pageable paging = PageRequest.of(0, 3 );
        Pageable paging = PageRequest.of(pageNumber, pageSize );
        Page<GuestEntity> pagedResult = this.guestPagingRepository.findAll(paging);
        List<GuestEntity> guestEntities = pagedResult.getContent();

        return ResponseEntity.ok(guestEntities);
    }

    // find user by name and surname
    // localhost:8088/api/finduser
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/api/finduser",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Transactional
    public ResponseEntity<List<GuestEntity>> findUser() {
        return ResponseEntity.ok(this.guestRepository.findUserWhereNameAndSurnameLike());
    }

    // find most frequent guest
    // localhost:8088/api/findmostfrequentguest
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/api/findmostfrequentguest",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Transactional
    public ResponseEntity<List<GuestEntity>> findMostFreuqentGuest() {
        return ResponseEntity.ok(this.guestRepository.findMostFrequentGuest());
    }

}
