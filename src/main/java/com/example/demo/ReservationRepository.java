package com.example.demo;

import com.example.demo.Entities.ReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<ReservationEntity, Long> {
    List<ReservationEntity> findAll();
    //Optional<ReservationEntity> findById(Long id);
    void deleteById(Long id);
}
