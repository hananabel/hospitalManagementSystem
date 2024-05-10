package com.example.hospitalManagementSystem.dtos;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AppointmentDTO {

    private LocalDateTime appointmentTime;
    private String doctorName;
    private String patientName;
}
