package com.fpmislata.tienda_back.domain.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CartTest {

    private Cart cart;
    private List<CartItem> items;

    @BeforeEach
    void setUp() {
        items = new ArrayList<>();
        items.add(new CartItem("item1", 2));
        items.add(new CartItem("item2", 3));
        
        cart = new Cart(1, 5, 250.0, items);
    }

    @Nested
    @DisplayName("Tests para la creación de Cart")
    class CartCreationTests {
        @Test
        @DisplayName("Debería crear Cart correctamente con datos válidos")
        void shouldCreateCartWithValidData() {
            assertNotNull(cart);
            assertEquals(1, cart.getId_cart());
            assertEquals(5, cart.getTotal_products());
            assertEquals(250.0, cart.getTotal_price());
            assertEquals(2, cart.getItems().size());
        }

        @Test
        @DisplayName("Debería crear Cart vacío")
        void shouldCreateEmptyCart() {
            Cart emptyCart = new Cart(2, 0, 0.0, new ArrayList<>());

            assertNotNull(emptyCart);
            assertEquals(2, emptyCart.getId_cart());
            assertEquals(0, emptyCart.getTotal_products());
            assertEquals(0.0, emptyCart.getTotal_price());
            assertTrue(emptyCart.getItems().isEmpty());
        }
    }

    @Nested
    @DisplayName("Tests para getters y setters")
    class GettersAndSettersTests {
        @Test
        @DisplayName("Debería actualizar id_cart correctamente")
        void shouldUpdateIdCart() {
            cart.setId_cart(10);
            assertEquals(10, cart.getId_cart());
        }

        @Test
        @DisplayName("Debería actualizar total_products correctamente")
        void shouldUpdateTotalProducts() {
            cart.setTotal_products(10);
            assertEquals(10, cart.getTotal_products());
        }

        @Test
        @DisplayName("Debería actualizar total_price correctamente")
        void shouldUpdateTotalPrice() {
            cart.setTotal_price(500.0);
            assertEquals(500.0, cart.getTotal_price());
        }

        @Test
        @DisplayName("Debería actualizar items correctamente")
        void shouldUpdateItems() {
            List<CartItem> newItems = new ArrayList<>();
            newItems.add(new CartItem("item3", 1));
            
            cart.setItems(newItems);
            
            assertEquals(1, cart.getItems().size());
            assertEquals("item3", cart.getItems().get(0).getId_cart());
        }
    }

    @Nested
    @DisplayName("Tests para operaciones con items")
    class ItemOperationsTests {
        @Test
        @DisplayName("Debería permitir agregar items a la lista")
        void shouldAllowAddingItems() {
            cart.getItems().add(new CartItem("item3", 1));
            
            assertEquals(3, cart.getItems().size());
        }

        @Test
        @DisplayName("Debería permitir remover items de la lista")
        void shouldAllowRemovingItems() {
            cart.getItems().remove(0);
            
            assertEquals(1, cart.getItems().size());
        }

        @Test
        @DisplayName("Debería manejar lista null de items")
        void shouldHandleNullItems() {
            cart.setItems(null);
            
            assertNull(cart.getItems());
        }
    }

    @Nested
    @DisplayName("Tests para validación de datos")
    class DataValidationTests {
        @Test
        @DisplayName("Debería aceptar total_price negativo")
        void shouldAcceptNegativeTotalPrice() {
            cart.setTotal_price(-100.0);
            assertEquals(-100.0, cart.getTotal_price());
        }

        @Test
        @DisplayName("Debería aceptar total_products negativo")
        void shouldAcceptNegativeTotalProducts() {
            cart.setTotal_products(-5);
            assertEquals(-5, cart.getTotal_products());
        }

        @Test
        @DisplayName("Debería aceptar id_cart cero")
        void shouldAcceptZeroIdCart() {
            cart.setId_cart(0);
            assertEquals(0, cart.getId_cart());
        }
    }
}
