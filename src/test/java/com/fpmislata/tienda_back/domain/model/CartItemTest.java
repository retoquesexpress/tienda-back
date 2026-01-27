package com.fpmislata.tienda_back.domain.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CartItemTest {

    private CartItem cartItem;

    @BeforeEach
    void setUp() {
        cartItem = new CartItem("item123", 5);
    }

    @Nested
    @DisplayName("Tests para la creación de CartItem")
    class CartItemCreationTests {
        @Test
        @DisplayName("Debería crear CartItem correctamente con datos válidos")
        void shouldCreateCartItemWithValidData() {
            assertNotNull(cartItem);
            assertEquals("item123", cartItem.getId_cart());
            assertEquals(5, cartItem.getQuantity());
        }

        @Test
        @DisplayName("Debería crear CartItem con quantity cero")
        void shouldCreateCartItemWithZeroQuantity() {
            CartItem item = new CartItem("item456", 0);

            assertNotNull(item);
            assertEquals("item456", item.getId_cart());
            assertEquals(0, item.getQuantity());
        }

        @Test
        @DisplayName("Debería crear CartItem con id null")
        void shouldCreateCartItemWithNullId() {
            CartItem item = new CartItem(null, 3);

            assertNotNull(item);
            assertNull(item.getId_cart());
            assertEquals(3, item.getQuantity());
        }
    }

    @Nested
    @DisplayName("Tests para getters y setters")
    class GettersAndSettersTests {
        @Test
        @DisplayName("Debería actualizar id_cart correctamente")
        void shouldUpdateIdCart() {
            cartItem.setId_cart("newItem789");
            assertEquals("newItem789", cartItem.getId_cart());
        }

        @Test
        @DisplayName("Debería actualizar quantity correctamente")
        void shouldUpdateQuantity() {
            cartItem.setQuantity(10);
            assertEquals(10, cartItem.getQuantity());
        }

        @Test
        @DisplayName("Debería permitir setear id_cart a null")
        void shouldAllowSettingIdCartToNull() {
            cartItem.setId_cart(null);
            assertNull(cartItem.getId_cart());
        }

        @Test
        @DisplayName("Debería permitir quantity negativo")
        void shouldAllowNegativeQuantity() {
            cartItem.setQuantity(-5);
            assertEquals(-5, cartItem.getQuantity());
        }
    }

    @Nested
    @DisplayName("Tests para operaciones con quantity")
    class QuantityOperationsTests {
        @Test
        @DisplayName("Debería incrementar quantity correctamente")
        void shouldIncrementQuantity() {
            int originalQuantity = cartItem.getQuantity();
            cartItem.setQuantity(originalQuantity + 1);
            
            assertEquals(6, cartItem.getQuantity());
        }

        @Test
        @DisplayName("Debería decrementar quantity correctamente")
        void shouldDecrementQuantity() {
            int originalQuantity = cartItem.getQuantity();
            cartItem.setQuantity(originalQuantity - 1);
            
            assertEquals(4, cartItem.getQuantity());
        }

        @Test
        @DisplayName("Debería resetear quantity a cero")
        void shouldResetQuantityToZero() {
            cartItem.setQuantity(0);
            assertEquals(0, cartItem.getQuantity());
        }
    }

    @Nested
    @DisplayName("Tests para validación de datos")
    class DataValidationTests {
        @Test
        @DisplayName("Debería manejar id_cart vacío")
        void shouldHandleEmptyIdCart() {
            cartItem.setId_cart("");
            assertEquals("", cartItem.getId_cart());
        }

        @Test
        @DisplayName("Debería manejar id_cart con espacios")
        void shouldHandleIdCartWithSpaces() {
            cartItem.setId_cart("  item with spaces  ");
            assertEquals("  item with spaces  ", cartItem.getId_cart());
        }

        @Test
        @DisplayName("Debería manejar quantity muy grande")
        void shouldHandleVeryLargeQuantity() {
            cartItem.setQuantity(Integer.MAX_VALUE);
            assertEquals(Integer.MAX_VALUE, cartItem.getQuantity());
        }

        @Test
        @DisplayName("Debería manejar quantity muy pequeño")
        void shouldHandleVerySmallQuantity() {
            cartItem.setQuantity(Integer.MIN_VALUE);
            assertEquals(Integer.MIN_VALUE, cartItem.getQuantity());
        }
    }
}
