package com.example.demo;

import com.example.demo.Entities.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<RoomEntity, Long> {
}
