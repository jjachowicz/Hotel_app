package com.example.demo.Entities;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "reservations")
public class ReservationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name="guest_id", nullable=false)
    private GuestEntity guest;

    @ManyToOne
    @JoinColumn(name="room_id", nullable=false)
    private RoomEntity room;

    @Column(name = "reservation_date", nullable = false)
    private Date reservationDate;


    public ReservationEntity(long id, GuestEntity guest, RoomEntity room, Date reservationDate) {
        this.id = id;
        this.guest = guest;
        this.room = room;
        this.reservationDate = reservationDate;
    }

    public ReservationEntity() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public GuestEntity getGuest() {
        return guest;
    }

    public void setGuest(GuestEntity guest) {
        this.guest = guest;
    }

    public RoomEntity getRoom() {
        return room;
    }

    public void setRoom(RoomEntity room) {
        this.room = room;
    }

    public Date getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(Date reservationDate) {
        this.reservationDate = reservationDate;
    }

}
