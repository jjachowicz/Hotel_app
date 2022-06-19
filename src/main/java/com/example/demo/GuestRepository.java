package com.example.demo;

import com.example.demo.Entities.GuestEntity;
import com.example.demo.Entities.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GuestRepository extends JpaRepository<GuestEntity, Long> {

    List<GuestEntity> findAll();
    Optional<GuestEntity> findById(Long id);
    List<GuestEntity> findAllByOrderByNameAsc();
    List<GuestEntity> findAllByOrderByNameDesc();

    @Query(value = "SELECT * FROM guests WHERE name LIKE 'z%' AND surname LIKE 'e%'",
            nativeQuery = true)
    List<GuestEntity> findUserWhereNameAndSurnameLike();

    // find most frequent guest
    @Query(value = "SELECT * FROM guests g LEFT JOIN reservations re ON g.id = re.guest_id GROUP BY guest_id ORDER BY Count(guest_id) DESC LIMIT 1",
            nativeQuery = true)

    List<GuestEntity> findMostFrequentGuest();

}

