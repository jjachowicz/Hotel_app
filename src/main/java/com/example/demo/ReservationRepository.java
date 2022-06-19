package com.example.demo;

import com.example.demo.Entities.ReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<ReservationEntity, Long> {
    List<ReservationEntity> findAll();
    Optional<ReservationEntity> findById(Long id);
    void deleteById(Long id);

    // select all reservations of a given guest
    @Query(value = "SELECT * FROM reservations r LEFT JOIN guests g ON r.guest_id = g.id WHERE g.id = :id",
            nativeQuery = true)

    List<ReservationEntity> findReservationEntities(@Param("id") Long id);

    // select all reservations of a given room
    @Query(value = "SELECT * FROM reservations r LEFT JOIN rooms rm ON r.room_id = rm.id WHERE rm.id = :id",
            nativeQuery = true)

    List<ReservationEntity> findReservationEntitiesByRoomid(@Param("id") Long id);


}
