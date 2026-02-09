package com.fpmislata.tienda_back.domain.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BookingTest {

    private Booking booking;
    private List<BookingItem> items;
    private User user;

    @BeforeEach
    void setUp() {
        Service service = new Service(1, "Test Service", "Description", 50.0, "url", null);
        items = new ArrayList<>();
        items.add(new BookingItem(1, 2, LocalDateTime.now(), service));
        items.add(new BookingItem(2, 3, LocalDateTime.now(), service));

        user = new User(1, "User", "User@gmail.com", "user", "user1234", "123456789", "123 Street", null, "USER");

        booking = new Booking(1, 250.0, items, user);
    }

    @Nested
    @DisplayName("Tests para la creación de Booking")
    class BookingCreationTests {
        @Test
        @DisplayName("Debería crear Booking correctamente con datos válidos")
        void shouldCreateBookingWithValidData() {

            assertNotNull(booking);
            assertEquals(1, booking.getId_booking());
            assertEquals(250.0, booking.getTotal_price());
            assertEquals(2, booking.getItems().size());
            assertEquals(user, booking.getUser());

        }

        @Test
        @DisplayName("Debería crear Booking vacío")
        void shouldCreateEmptyBooking() {
            Booking emptyBooking = new Booking(2, 0, new ArrayList<>(), null);

            assertNotNull(emptyBooking);
            assertEquals(2, emptyBooking.getId_booking());
            assertEquals(0.0, emptyBooking.getTotal_price());
            assertTrue(emptyBooking.getItems().isEmpty());
            assertTrue(emptyBooking.getUser() == null);

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

        // @Test
        // @DisplayName("Debería actualizar total_products correctamente")
        // void shouldUpdateTotalProducts() {
        // booking.setTotal_products(10);
        // assertEquals(10, booking.getTotal_products());
        // }

        @Test
        @DisplayName("Debería actualizar total_price correctamente")
        void shouldUpdateTotalPrice() {
            booking.setTotal_price(500.0);
            assertEquals(500.0, booking.getTotal_price());
        }

        @Test
        @DisplayName("Debería actualizar items correctamente")
        void shouldUpdateItems() {
            Service service = new Service(1, "Test Service", "Description", 50.0, "url", null);
            List<BookingItem> newItems = new ArrayList<>();
            newItems.add(new BookingItem(3, 1, LocalDateTime.now(), service));

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
            Service service = new Service(1, "Test Service", "Description", 50.0, "url", null);
            booking.getItems().add(new BookingItem(3, 1, LocalDateTime.now(), service));

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

    // @Nested
    // @DisplayName("Tests para validación de datos")
    // class DataValidationTests {
    // @Test
    // @DisplayName("Debería aceptar total_price negativo")
    // void shouldAcceptNegativeTotalPrice() {
    // booking.setTotal_price(-100.0);
    // assertEquals(-100.0, booking.getTotal_price());
    // }

    // @Test
    // @DisplayName("Debería aceptar total_products negativo")
    // void shouldAcceptNegativeTotalProducts() {
    // booking.setTotal_products(-5);
    // assertEquals(-5, booking.getTotal_products());
    // }
    @Nested
    @DisplayName("Tests para validación de datos")
    class DataValidationTests {
        @Test
        @DisplayName("Debería aceptar total_price negativo")
        void shouldAcceptNegativeTotalPrice() {
            booking.setTotal_price(-100.0);
            assertEquals(-100.0, booking.getTotal_price());
        }

        // @Test
        // @DisplayName("Debería aceptar total_products negativo")
        // void shouldAcceptNegativeTotalProducts() {
        // booking.setTotal_products(-5);
        // assertEquals(-5, booking.getTotal_products());
        // }

        @Test
        @DisplayName("Debería aceptar id_booking cero")
        void shouldAcceptZeroIdBooking() {
            booking.setId_booking(0);
            assertEquals(0, booking.getId_booking());
        }
    }

}
