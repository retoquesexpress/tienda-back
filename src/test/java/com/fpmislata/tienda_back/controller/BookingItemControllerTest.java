package com.fpmislata.tienda_back.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fpmislata.tienda_back.controller.webModel.request.BookingItemInsertRequest;
import com.fpmislata.tienda_back.domain.service.BookingItemService;
import com.fpmislata.tienda_back.domain.service.dto.BookingItemDto;
import com.fpmislata.tienda_back.domain.service.dto.CategoryDto;
import com.fpmislata.tienda_back.domain.service.dto.ServiceDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BookingItemController.class)
@AutoConfigureMockMvc(addFilters = false)
class BookingItemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookingItemService bookingItemService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Test GET /api/booking-items should return OK")
    void findAll_ShouldReturnOk() throws Exception {
        CategoryDto categoryDto = new CategoryDto(1, "Cat");
        ServiceDto serviceDto = new ServiceDto(1, "Name", "Desc", 10.0, "url", categoryDto);
        BookingItemDto dto = new BookingItemDto(1, 1, 2, java.time.LocalDateTime.now(), serviceDto);
        when(bookingItemService.findAll()).thenReturn(List.of(dto));

        mockMvc.perform(get("/api/booking-items"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].idBookingItem").value(1))
                .andExpect(jsonPath("$[0].quantity").value(2));

        verify(bookingItemService, times(1)).findAll();
    }

    @Test
    @DisplayName("Test GET /api/booking-items/{id} should return OK")
    void findById_ShouldReturnOk() throws Exception {
        Integer id = 1;
        CategoryDto categoryDto = new CategoryDto(1, "Cat");
        ServiceDto serviceDto = new ServiceDto(1, "Name", "Desc", 10.0, "url", categoryDto);
        BookingItemDto dto = new BookingItemDto(id, 1, 2, java.time.LocalDateTime.now(), serviceDto);
        when(bookingItemService.findById(id)).thenReturn(dto);

        mockMvc.perform(get("/api/booking-items/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idBookingItem").value(id))
                .andExpect(jsonPath("$.quantity").value(2));

        verify(bookingItemService, times(1)).findById(id);
    }

    @Test
    @DisplayName("Test POST /api/booking-items should return CREATED")
    void save_ShouldReturnCreated() throws Exception {
        BookingItemInsertRequest request = new BookingItemInsertRequest(1, 1, 2, java.time.LocalDateTime.now());
        CategoryDto categoryDto = new CategoryDto(1, "Cat");
        ServiceDto serviceDto = new ServiceDto(1, "Name", "Desc", 10.0, "url", categoryDto);
        BookingItemDto savedDto = new BookingItemDto(1, 1, 2, java.time.LocalDateTime.now(), serviceDto);

        when(bookingItemService.save(any(BookingItemDto.class))).thenReturn(savedDto);

        mockMvc.perform(post("/api/booking-items")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.idBookingItem").value(1));

        verify(bookingItemService, times(1)).save(any(BookingItemDto.class));
    }


    @Test
    @DisplayName("Test DELETE /api/booking-items/{id} should return NO_CONTENT")
    void delete_ShouldReturnNoContent() throws Exception {
        Integer id = 1;
        mockMvc.perform(delete("/api/booking-items/{id}", id))
                .andExpect(status().isNoContent());

        verify(bookingItemService, times(1)).delete(id);
    }

    @Test
    @DisplayName("Test PATCH /api/booking-items/{id}/increase should return NO_CONTENT")
    void increaseQuantity_ShouldReturnNoContent() throws Exception {
        Integer id = 1;
        mockMvc.perform(patch("/api/booking-items/{id}/increase", id))
                .andExpect(status().isNoContent());

        verify(bookingItemService, times(1)).increaseQuantityById(id);
    }

    @Test
    @DisplayName("Test PATCH /api/booking-items/{id}/decrease should return NO_CONTENT")
    void decreaseQuantity_ShouldReturnNoContent() throws Exception {
        Integer id = 1;
        mockMvc.perform(patch("/api/booking-items/{id}/decrease", id))
                .andExpect(status().isNoContent());

        verify(bookingItemService, times(1)).decreaseQuantityById(id);
    }
}
