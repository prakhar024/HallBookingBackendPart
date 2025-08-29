package com.example.hallbooking.dto;

import lombok.Data;

@Data
public class BookingRequest {
    private String customerName;
    private String customerPhone;
    private String eventDate;
    private String status;
    private Long hallId;
    private Long userId;
    private String bookingDate;
}
