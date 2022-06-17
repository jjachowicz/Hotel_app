package com.example.demo;

import com.example.demo.Entities.ReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<ReservationEntity, Long> {
    List<ReservationEntity> findAll();
    Optional<ReservationEntity> findById(Long id);
    void deleteById(Long id);

    //@Query(value = "SELECT * FROM reservations LEFT JOIN guests ON reservations.guest_id = guests.id",
            //nativeQuery = true)
    @Query(value = "SELECT * FROM reservations WHERE guest_id = 1",
            nativeQuery = true)
    List<ReservationEntity> findReservationEntities();
}
