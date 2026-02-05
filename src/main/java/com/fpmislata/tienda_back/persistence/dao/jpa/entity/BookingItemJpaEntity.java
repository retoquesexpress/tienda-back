package com.fpmislata.tienda_back.persistence.dao.jpa.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "booking_items")
public class BookingItemJpaEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_booking_item")
    private Integer idBookingItem;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_booking", nullable = false)
    private BookingJpaEntity booking;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_service", nullable = false)
    private ServiceJpaEntity service;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "booking_date", nullable = false)
    private LocalDate bookingDate;

    public BookingItemJpaEntity() {
    }

    public BookingItemJpaEntity(Integer idBookingItem, BookingJpaEntity booking, ServiceJpaEntity service,
            Integer quantity, LocalDate bookingDate) {
        this.idBookingItem = idBookingItem;
        this.booking = booking;
        this.service = service;
        this.quantity = quantity;
        this.bookingDate = bookingDate;
    }

    public Integer getIdBookingItem() {
        return idBookingItem;
    }

    public void setIdBookingItem(Integer idBookingItem) {
        this.idBookingItem = idBookingItem;
    }

    public BookingJpaEntity getBooking() {
        return booking;
    }

    public void setBooking(BookingJpaEntity booking) {
        this.booking = booking;
    }

    public ServiceJpaEntity getService() {
        return service;
    }

    public void setService(ServiceJpaEntity service) {
        this.service = service;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public LocalDate getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
    }
}
