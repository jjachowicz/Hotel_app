package com.example.demo;

import com.example.demo.Entities.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<RoomEntity, Long> {

    @Query(value = "SELECT * FROM rooms WHERE is_reserved = 0",
    nativeQuery = true)
    List<RoomEntity> findFreeRoomEntity();
}
