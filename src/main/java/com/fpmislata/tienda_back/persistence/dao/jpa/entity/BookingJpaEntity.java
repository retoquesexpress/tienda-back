package com.fpmislata.tienda_back.persistence.dao.jpa.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "booking")
public class BookingJpaEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_booking")
    private Integer idBooking;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user", nullable = false)
    private UserJpaEntity user;

    @Column(name = "total_price")
    private Double totalPrice;

    @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BookingItemJpaEntity> bookingItems = new ArrayList<>();

    public BookingJpaEntity() {
    }

    public BookingJpaEntity(Integer idBooking, Integer idUser, Double totalPrice) {
        this.idBooking = idBooking;
        this.user = user;
        this.totalPrice = totalPrice;
    }

    public Integer getIdBooking() {
        return idBooking;
    }

    public void setIdBooking(Integer idBooking) {
        this.idBooking = idBooking;
    }


    public UserJpaEntity getUser() {
        return user;
    }

    public void setUser(UserJpaEntity idUser) {
        this.user = idUser;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<BookingItemJpaEntity> getBookingItems() {
        return bookingItems;
    }

    public void setBookingItems(List<BookingItemJpaEntity> bookingItems) {
        this.bookingItems = bookingItems;
    }
}


