package com.fpmislata.tienda_back.domain.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BookingItemTest {

    private BookingItem bookingItem;

    @BeforeEach
    void setUp() {
        bookingItem = new BookingItem("item123", 5);
    }

    @Nested
    @DisplayName("Tests para la creación de BookingItem")
    class BookingItemCreationTests {
        @Test
        @DisplayName("Debería crear BookingItem correctamente con datos válidos")
        void shouldCreateBookingItemWithValidData() {
            assertNotNull(bookingItem);
            assertEquals("item123", bookingItem.getId_booking());
            assertEquals(5, bookingItem.getQuantity());
        }

        @Test
        @DisplayName("Debería crear BookingItem con quantity cero")
        void shouldCreateBookingItemWithZeroQuantity() {
            BookingItem item = new BookingItem("item456", 0);

            assertNotNull(item);
            assertEquals("item456", item.getId_booking());
            assertEquals(0, item.getQuantity());
        }

        @Test
        @DisplayName("Debería crear BookingItem con id null")
        void shouldCreateBookingItemWithNullId() {
            BookingItem item = new BookingItem(null, 3);

            assertNotNull(item);
            assertNull(item.getId_booking());
            assertEquals(3, item.getQuantity());
        }
    }

    @Nested
    @DisplayName("Tests para getters y setters")
    class GettersAndSettersTests {
        @Test
        @DisplayName("Debería actualizar id_booking correctamente")
        void shouldUpdateIdBooking() {
            bookingItem.setId_booking("newItem789");
            assertEquals("newItem789", bookingItem.getId_booking());
        }

        @Test
        @DisplayName("Debería actualizar quantity correctamente")
        void shouldUpdateQuantity() {
            bookingItem.setQuantity(10);
            assertEquals(10, bookingItem.getQuantity());
        }

        @Test
        @DisplayName("Debería permitir setear id_booking a null")
        void shouldAllowSettingIdBookingToNull() {
            bookingItem.setId_booking(null);
            assertNull(bookingItem.getId_booking());
        }

        @Test
        @DisplayName("Debería permitir quantity negativo")
        void shouldAllowNegativeQuantity() {
            bookingItem.setQuantity(-5);
            assertEquals(-5, bookingItem.getQuantity());
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
        @DisplayName("Debería manejar id_booking vacío")
        void shouldHandleEmptyIdBooking() {
            bookingItem.setId_booking("");
            assertEquals("", bookingItem.getId_booking());
        }

        @Test
        @DisplayName("Debería manejar id_booking con espacios")
        void shouldHandleIdBookingWithSpaces() {
            bookingItem.setId_booking("  item with spaces  ");
            assertEquals("  item with spaces  ", bookingItem.getId_booking());
        }

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
