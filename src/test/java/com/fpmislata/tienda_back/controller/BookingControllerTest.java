package com.fpmislata.tienda_back.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fpmislata.tienda_back.controller.webModel.request.BookingInsertRequest;
import com.fpmislata.tienda_back.domain.service.BookingService;
import com.fpmislata.tienda_back.domain.service.dto.BookingDto;
import com.fpmislata.tienda_back.domain.service.dto.UserDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BookingController.class)
@AutoConfigureMockMvc(addFilters = false)
class BookingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookingService bookingService;

    @Autowired
    private ObjectMapper objectMapper;

//    @Test
//    @DisplayName("Test GET /api/bookings should return OK")
//    void findAll_ShouldReturnOk() throws Exception {
//        UserDto userDto = new UserDto(1, null, null, null, null, null, null, null, null);
//        BookingDto bookingDto = new BookingDto(1, 100.0, new ArrayList<>(), userDto);
//        when(bookingService.findAll()).thenReturn(List.of(bookingDto));
//
//        mockMvc.perform(get("/api/bookings"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$[0].idBooking").value(1))
//                .andExpect(jsonPath("$[0].total_price").value(100.0));
//
//        verify(bookingService, times(1)).findAll();
//    }

//    @Test
//    @DisplayName("Test GET /api/bookings/{id} should return OK")
//    void findById_ShouldReturnOk() throws Exception {
//        Integer id = 1;
//        UserDto userDto = new UserDto(1, null, null, null, null, null, null, null, null);
//        BookingDto bookingDto = new BookingDto(id, 100.0, new ArrayList<>(), userDto);
//        when(bookingService.findById(id)).thenReturn(bookingDto);
//
//        mockMvc.perform(get("/api/bookings/{id}", id))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.idBooking").value(id))
//                .andExpect(jsonPath("$.total_price").value(100.0));
//
//        verify(bookingService, times(1)).findById(id);
//    }

    @Test
    @DisplayName("Test GET /api/bookings/booking/{idUser} should return OK")
    void findFutureBookingsByUser_ShouldReturnOk() throws Exception {
        Integer userId = 1;
        UserDto userDto = new UserDto(userId, null, null, null, null, null, null, null, null);
        BookingDto bookingDto = new BookingDto(1, 100.0, new ArrayList<>(), userDto);
        when(bookingService.findAllBookingsByUserWhenBookingDateIsFuture(userId)).thenReturn(List.of(bookingDto));

        mockMvc.perform(get("/api/bookings/booking/{idUser}", userId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].idUser").value(userId));

        verify(bookingService, times(1)).findAllBookingsByUserWhenBookingDateIsFuture(userId);
    }

    @Test
    @DisplayName("Test POST /api/bookings should return CREATED")
    void save_ShouldReturnCreated() throws Exception {
        BookingInsertRequest request = new BookingInsertRequest(1, new ArrayList<>());
        UserDto userDto = new UserDto(1, null, null, null, null, null, null, null, null);
        BookingDto savedBooking = new BookingDto(1, 100.0, new ArrayList<>(), userDto);

        when(bookingService.save(any(BookingDto.class))).thenReturn(savedBooking);

        mockMvc.perform(post("/api/bookings")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.idBooking").value(1));

        verify(bookingService, times(1)).save(any(BookingDto.class));
    }

    @Test
    @DisplayName("Test DELETE /api/bookings/{id} should return NO_CONTENT")
    void delete_ShouldReturnNoContent() throws Exception {
        Integer id = 1;
        mockMvc.perform(delete("/api/bookings/{id}", id))
                .andExpect(status().isNoContent());

        verify(bookingService, times(1)).delete(id);
    }
}
