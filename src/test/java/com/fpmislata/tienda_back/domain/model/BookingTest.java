package com.fpmislata.tienda_back.domain.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BookingTest {

    private Booking booking;
    private List<BookingItem> items;

    @BeforeEach
    void setUp() {
        items = new ArrayList<>();
        items.add(new BookingItem(1, 2, LocalDate.now(), null));
        items.add(new BookingItem(2, 3, LocalDate.now(), null));

        booking = new Booking(1, 5, 250.0, items);
    }

    @Nested
    @DisplayName("Tests para la creación de Booking")
    class BookingCreationTests {
        @Test
        @DisplayName("Debería crear Booking correctamente con datos válidos")
        void shouldCreateBookingWithValidData() {
            assertNotNull(booking);
            assertEquals(1, booking.getId_booking());
            assertEquals(5, booking.getTotal_products());
            assertEquals(250.0, booking.getTotal_price());
            assertEquals(2, booking.getItems().size());
        }

        @Test
        @DisplayName("Debería crear Booking vacío")
        void shouldCreateEmptyBooking() {
            Booking emptyBooking = new Booking(2, 0, 0.0, new ArrayList<>());

            assertNotNull(emptyBooking);
            assertEquals(2, emptyBooking.getId_booking());
            assertEquals(0, emptyBooking.getTotal_products());
            assertEquals(0.0, emptyBooking.getTotal_price());
            assertTrue(emptyBooking.getItems().isEmpty());
        }
    }

    @Nested
    @DisplayName("Tests para getters y setters")
    class GettersAndSettersTests {
        @Test
        @DisplayName("Debería actualizar id_booking correctamente")
        void shouldUpdateIdBooking() {
            booking.setId_booking(10);
            assertEquals(10, booking.getId_booking());
        }

        @Test
        @DisplayName("Debería actualizar total_products correctamente")
        void shouldUpdateTotalProducts() {
            booking.setTotal_products(10);
            assertEquals(10, booking.getTotal_products());
        }

        @Test
        @DisplayName("Debería actualizar total_price correctamente")
        void shouldUpdateTotalPrice() {
            booking.setTotal_price(500.0);
            assertEquals(500.0, booking.getTotal_price());
        }

        @Test
        @DisplayName("Debería actualizar items correctamente")
        void shouldUpdateItems() {
            List<BookingItem> newItems = new ArrayList<>();
            newItems.add(new BookingItem(3, 1, LocalDate.now(), null));

            booking.setItems(newItems);

            assertEquals(1, booking.getItems().size());
            assertEquals(3, booking.getItems().get(0).getIdBookingItem());
        }
    }

    @Nested
    @DisplayName("Tests para operaciones con items")
    class ItemOperationsTests {
        @Test
        @DisplayName("Debería permitir agregar items a la lista")
        void shouldAllowAddingItems() {
            booking.getItems().add(new BookingItem(3, 1, LocalDate.now(), null));

            assertEquals(3, booking.getItems().size());
        }

        @Test
        @DisplayName("Debería permitir remover items de la lista")
        void shouldAllowRemovingItems() {
            booking.getItems().remove(0);

            assertEquals(1, booking.getItems().size());
        }

        @Test
        @DisplayName("Debería manejar lista null de items")
        void shouldHandleNullItems() {
            booking.setItems(null);

            assertNull(booking.getItems());
        }
    }

    @Nested
    @DisplayName("Tests para validación de datos")
    class DataValidationTests {
        @Test
        @DisplayName("Debería aceptar total_price negativo")
        void shouldAcceptNegativeTotalPrice() {
            booking.setTotal_price(-100.0);
            assertEquals(-100.0, booking.getTotal_price());
        }

        @Test
        @DisplayName("Debería aceptar total_products negativo")
        void shouldAcceptNegativeTotalProducts() {
            booking.setTotal_products(-5);
            assertEquals(-5, booking.getTotal_products());
        }

        @Test
        @DisplayName("Debería aceptar id_booking cero")
        void shouldAcceptZeroIdBooking() {
            booking.setId_booking(0);
            assertEquals(0, booking.getId_booking());
        }
    }
}
