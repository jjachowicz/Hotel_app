package com.example.demo.Entities;


import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "rooms")
public class RoomEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "size", nullable = false)
    private int size;

    @Column(name = "price_per_night", nullable = false)
    private double pricePerNight;

    @Column(name = "is_reserved", nullable = false)
    private boolean isReserved;

    @OneToMany(mappedBy = "room")
    private List<ReservationEntity> reservations;

    public RoomEntity() {

    }

    public RoomEntity(long id, int size, double pricePerNight, boolean isReserved) {
        this.id = id;
        this.size = size;
        this.pricePerNight = pricePerNight;
        this.isReserved = isReserved;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(double pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public boolean isReserved() {
        return isReserved;
    }

    public void setReserved(boolean reserved) {
        isReserved = reserved;
    }
}
