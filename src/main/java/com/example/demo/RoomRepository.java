package com.example.demo;

import com.example.demo.Entities.GuestEntity;
import com.example.demo.Entities.ReservationEntity;
import com.example.demo.Entities.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<RoomEntity, Long> {

    List<RoomEntity> findAll();

    @Query(value = "SELECT * FROM rooms WHERE is_reserved = 0",
    nativeQuery = true)
    List<RoomEntity> findFreeRoomEntity();

    Optional<RoomEntity> findById(Long id);

    @Query(value = "SELECT * FROM rooms WHERE size > 20",
            nativeQuery = true)
    List<RoomEntity> findRoomsWhereSizeMoreThan();

    // find most reserved room
    @Query(value = "SELECT * FROM rooms ro LEFT JOIN reservations re ON ro.id = re.room_id GROUP BY room_id ORDER BY Count(room_id) DESC LIMIT 1",
            nativeQuery = true)

    List<RoomEntity> findMostVisitedRoom();

}
