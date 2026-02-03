package com.fpmislata.tienda_back.domain.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class BookingItemTest {

    private BookingItem bookingItem;

    @BeforeEach
    void setUp() {
        bookingItem = new BookingItem(1, 5, LocalDate.of(2026, 2, 3), null);
    }

    @Nested
    @DisplayName("Tests para la creación de BookingItem")
    class BookingItemCreationTests {
        @Test
        @DisplayName("Debería crear BookingItem correctamente con datos válidos")
        void shouldCreateBookingItemWithValidData() {
            assertNotNull(bookingItem);
            assertEquals(1, bookingItem.getIdBookingItem());
            assertEquals(5, bookingItem.getQuantity());
            assertEquals(LocalDate.of(2026, 2, 3), bookingItem.getBookingDate());
        }

        @Test
        @DisplayName("Debería crear BookingItem con quantity cero")
        void shouldCreateBookingItemWithZeroQuantity() {
            BookingItem item = new BookingItem(2, 0, LocalDate.now(), null);

            assertNotNull(item);
            assertEquals(2, item.getIdBookingItem());
            assertEquals(0, item.getQuantity());
        }

        @Test
        @DisplayName("Debería crear BookingItem con id null")
        void shouldCreateBookingItemWithNullId() {
            BookingItem item = new BookingItem(null, 3, LocalDate.now(), null);

            assertNotNull(item);
            assertNull(item.getIdBookingItem());
            assertEquals(3, item.getQuantity());
        }
    }

    @Nested
    @DisplayName("Tests para getters y setters")
    class GettersAndSettersTests {
        @Test
        @DisplayName("Debería actualizar idBookingItem correctamente")
        void shouldUpdateIdBookingItem() {
            bookingItem.setIdBookingItem(789);
            assertEquals(789, bookingItem.getIdBookingItem());
        }

        @Test
        @DisplayName("Debería actualizar quantity correctamente")
        void shouldUpdateQuantity() {
            bookingItem.setQuantity(10);
            assertEquals(10, bookingItem.getQuantity());
        }

        @Test
        @DisplayName("Debería permitir setear idBookingItem a null")
        void shouldAllowSettingIdBookingItemToNull() {
            bookingItem.setIdBookingItem(null);
            assertNull(bookingItem.getIdBookingItem());
        }

        @Test
        @DisplayName("Debería permitir quantity negativo")
        void shouldAllowNegativeQuantity() {
            bookingItem.setQuantity(-5);
            assertEquals(-5, bookingItem.getQuantity());
        }

        @Test
        @DisplayName("Debería actualizar bookingDate correctamente")
        void shouldUpdateBookingDate() {
            LocalDate newDate = LocalDate.of(2026, 12, 25);
            bookingItem.setBookingDate(newDate);
            assertEquals(newDate, bookingItem.getBookingDate());
        }
    }

    @Nested
    @DisplayName("Tests para operaciones con quantity")
    class QuantityOperationsTests {
        @Test
        @DisplayName("Debería incrementar quantity correctamente")
        void shouldIncrementQuantity() {
            int originalQuantity = bookingItem.getQuantity();
            bookingItem.setQuantity(originalQuantity + 1);

            assertEquals(6, bookingItem.getQuantity());
        }

        @Test
        @DisplayName("Debería decrementar quantity correctamente")
        void shouldDecrementQuantity() {
            int originalQuantity = bookingItem.getQuantity();
            bookingItem.setQuantity(originalQuantity - 1);

            assertEquals(4, bookingItem.getQuantity());
        }

        @Test
        @DisplayName("Debería resetear quantity a cero")
        void shouldResetQuantityToZero() {
            bookingItem.setQuantity(0);
            assertEquals(0, bookingItem.getQuantity());
        }
    }

    @Nested
    @DisplayName("Tests para validación de datos")
    class DataValidationTests {
        @Test
        @DisplayName("Debería manejar quantity muy grande")
        void shouldHandleVeryLargeQuantity() {
            bookingItem.setQuantity(Integer.MAX_VALUE);
            assertEquals(Integer.MAX_VALUE, bookingItem.getQuantity());
        }

        @Test
        @DisplayName("Debería manejar quantity muy pequeño")
        void shouldHandleVerySmallQuantity() {
            bookingItem.setQuantity(Integer.MIN_VALUE);
            assertEquals(Integer.MIN_VALUE, bookingItem.getQuantity());
        }
    }
}
