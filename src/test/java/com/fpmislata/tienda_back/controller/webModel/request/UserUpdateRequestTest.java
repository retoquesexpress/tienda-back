package com.fpmislata.tienda_back.controller.webModel.request;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class UserUpdateRequestTest {

    @Nested
    @DisplayName("Tests para la creación de UserUpdateRequest")
    class UserUpdateRequestCreationTests {
        @Test
        @DisplayName("Debería crear UserUpdateRequest correctamente con todos los campos")
        void shouldCreateUserUpdateRequestWithAllFields() {
            Date birthDate = new Date();
            UserUpdateRequest request = new UserUpdateRequest(1, "johndoe", "password123",
                    "john@example.com", "John Doe", "123456789", "123 Main St", birthDate, "USER");

            assertNotNull(request);
            assertEquals(1, request.idUser());
            assertEquals("johndoe", request.userName());
            assertEquals("password123", request.password());
            assertEquals("john@example.com", request.email());
            assertEquals("John Doe", request.name());
            assertEquals("123456789", request.phoneNumber());
            assertEquals("123 Main St", request.address());
            assertEquals(birthDate, request.birthDate());
            assertEquals("USER", request.role());
        }

        @Test
        @DisplayName("Debería crear UserUpdateRequest con campos opcionales null")
        void shouldCreateUserUpdateRequestWithNullOptionalFields() {
            UserUpdateRequest request = new UserUpdateRequest(1, "johndoe", "password123",
                    "john@example.com", "John Doe", null, null, null, "USER");

            assertNotNull(request);
            assertEquals(1, request.idUser());
            assertNull(request.phoneNumber());
            assertNull(request.address());
            assertNull(request.birthDate());
        }

        @Test
        @DisplayName("Debería crear UserUpdateRequest con idUser null")
        void shouldCreateUserUpdateRequestWithNullId() {
            UserUpdateRequest request = new UserUpdateRequest(null, "johndoe", "password123",
                    "john@example.com", "John Doe", "123456789", "123 Main St", null, "USER");

            assertNotNull(request);
            assertNull(request.idUser());
        }
    }

    @Nested
    @DisplayName("Tests para validación de campos requeridos")
    class RequiredFieldsTests {
        @Test
        @DisplayName("Debería permitir crear con userName null")
        void shouldAllowNullUserName() {
            UserUpdateRequest request = new UserUpdateRequest(1, null, "password123",
                    "john@example.com", "John Doe", "123456789", "123 Main St", null, "USER");

            assertNotNull(request);
            assertNull(request.userName());
        }

        @Test
        @DisplayName("Debería permitir crear con password null")
        void shouldAllowNullPassword() {
            UserUpdateRequest request = new UserUpdateRequest(1, "johndoe", null,
                    "john@example.com", "John Doe", "123456789", "123 Main St", null, "USER");

            assertNotNull(request);
            assertNull(request.password());
        }

        @Test
        @DisplayName("Debería permitir crear con email null")
        void shouldAllowNullEmail() {
            UserUpdateRequest request = new UserUpdateRequest(1, "johndoe", "password123",
                    null, "John Doe", "123456789", "123 Main St", null, "USER");

            assertNotNull(request);
            assertNull(request.email());
        }

        @Test
        @DisplayName("Debería permitir crear con name null")
        void shouldAllowNullName() {
            UserUpdateRequest request = new UserUpdateRequest(1, "johndoe", "password123",
                    "john@example.com", null, "123456789", "123 Main St", null, "USER");

            assertNotNull(request);
            assertNull(request.name());
        }

        @Test
        @DisplayName("Debería permitir crear con role null")
        void shouldAllowNullRole() {
            UserUpdateRequest request = new UserUpdateRequest(1, "johndoe", "password123",
                    "john@example.com", "John Doe", "123456789", "123 Main St", null, null);

            assertNotNull(request);
            assertNull(request.role());
        }
    }

    @Nested
    @DisplayName("Tests para diferentes roles")
    class RoleTests {
        @Test
        @DisplayName("Debería aceptar role USER")
        void shouldAcceptUserRole() {
            UserUpdateRequest request = new UserUpdateRequest(1, "johndoe", "password123",
                    "john@example.com", "John Doe", "123456789", "123 Main St", null, "USER");

            assertEquals("USER", request.role());
        }

        @Test
        @DisplayName("Debería aceptar role ADMIN")
        void shouldAcceptAdminRole() {
            UserUpdateRequest request = new UserUpdateRequest(1, "admin", "password123",
                    "admin@example.com", "Admin User", "123456789", "123 Main St", null, "ADMIN");

            assertEquals("ADMIN", request.role());
        }

        @Test
        @DisplayName("Debería aceptar cualquier string como role")
        void shouldAcceptAnyStringAsRole() {
            UserUpdateRequest request = new UserUpdateRequest(1, "johndoe", "password123",
                    "john@example.com", "John Doe", "123456789", "123 Main St", null, "CUSTOM_ROLE");

            assertEquals("CUSTOM_ROLE", request.role());
        }
    }

    @Nested
    @DisplayName("Tests para validación de formato de datos")
    class DataFormatTests {
        @Test
        @DisplayName("Debería aceptar email con formato válido")
        void shouldAcceptValidEmailFormat() {
            UserUpdateRequest request = new UserUpdateRequest(1, "johndoe", "password123",
                    "john.doe@example.com", "John Doe", "123456789", "123 Main St", null, "USER");

            assertEquals("john.doe@example.com", request.email());
        }

        @Test
        @DisplayName("Debería aceptar phoneNumber con diferentes formatos")
        void shouldAcceptDifferentPhoneNumberFormats() {
            UserUpdateRequest request1 = new UserUpdateRequest(1, "johndoe", "password123",
                    "john@example.com", "John Doe", "123-456-7890", "123 Main St", null, "USER");
            UserUpdateRequest request2 = new UserUpdateRequest(1, "johndoe", "password123",
                    "john@example.com", "John Doe", "+1234567890", "123 Main St", null, "USER");

            assertEquals("123-456-7890", request1.phoneNumber());
            assertEquals("+1234567890", request2.phoneNumber());
        }

        @Test
        @DisplayName("Debería aceptar address con múltiples líneas")
        void shouldAcceptMultiLineAddress() {
            UserUpdateRequest request = new UserUpdateRequest(1, "johndoe", "password123",
                    "john@example.com", "John Doe", "123456789", 
                    "123 Main St\nApt 4B\nNew York, NY 10001", null, "USER");

            assertTrue(request.address().contains("\n"));
        }
    }

    @Nested
    @DisplayName("Tests para igualdad de records")
    class RecordEqualityTests {
        @Test
        @DisplayName("Debería ser igual a otro UserUpdateRequest con los mismos valores")
        void shouldBeEqualToAnotherRequestWithSameValues() {
            Date birthDate = new Date();
            UserUpdateRequest request1 = new UserUpdateRequest(1, "johndoe", "password123",
                    "john@example.com", "John Doe", "123456789", "123 Main St", birthDate, "USER");
            UserUpdateRequest request2 = new UserUpdateRequest(1, "johndoe", "password123",
                    "john@example.com", "John Doe", "123456789", "123 Main St", birthDate, "USER");

            assertEquals(request1, request2);
            assertEquals(request1.hashCode(), request2.hashCode());
        }

        @Test
        @DisplayName("No debería ser igual a otro UserUpdateRequest con valores diferentes")
        void shouldNotBeEqualToAnotherRequestWithDifferentValues() {
            UserUpdateRequest request1 = new UserUpdateRequest(1, "johndoe", "password123",
                    "john@example.com", "John Doe", "123456789", "123 Main St", null, "USER");
            UserUpdateRequest request2 = new UserUpdateRequest(2, "janedoe", "password456",
                    "jane@example.com", "Jane Doe", "987654321", "456 Oak Ave", null, "ADMIN");

            assertNotEquals(request1, request2);
        }
    }
}
