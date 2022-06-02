package com.example.demo;

import com.example.demo.Entities.GuestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GuestRepository extends JpaRepository<GuestEntity, Long> {

    List<GuestEntity> findAll();
    Optional<GuestEntity> findById(Long id);
    List<GuestEntity> findAllByOrderByNameAsc();
    List<GuestEntity> findAllByOrderByNameDesc();

}

