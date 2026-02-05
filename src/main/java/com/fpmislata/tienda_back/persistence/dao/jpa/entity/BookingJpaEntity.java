package com.fpmislata.tienda_back.persistence.dao.jpa.entity;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "booking")
public class BookingJpaEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_booking")
    private Integer idBooking;

    @Column(name = "id_user")
    private Integer idUser;

    @Column(name = "total_price")
    private Double totalPrice;

    public BookingJpaEntity() {
    }

    public BookingJpaEntity(Integer idBooking, Integer idUser, Double totalPrice) {
        this.idBooking = idBooking;
        this.idUser = idUser;
        this.totalPrice = totalPrice;
    }

    public Integer getIdBooking() {
        return idBooking;
    }

    public void setIdBooking(Integer idBooking) {
        this.idBooking = idBooking;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
