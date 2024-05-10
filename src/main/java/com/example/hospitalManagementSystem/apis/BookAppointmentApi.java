package com.example.hospitalManagementSystem.apis;

import com.example.hospitalManagementSystem.dtos.AppointmentDTO;
import com.example.hospitalManagementSystem.models.Appointment;
import com.example.hospitalManagementSystem.response.ApiResponse;
import com.example.hospitalManagementSystem.services.AppointmentService;
import com.example.hospitalManagementSystem.validation.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Controller
public class BookAppointmentApi {
    private final AppointmentService appointmentService;
    private final ValidationUtils validationUtils;

    @Autowired
    public BookAppointmentApi(AppointmentService appointmentService, ValidationUtils validationUtils) {
        this.appointmentService = appointmentService;
        this.validationUtils = validationUtils;
    }

    public ResponseEntity<ApiResponse<AppointmentDTO>> bookAppointment(Appointment appointment, Long doctorId, Long patientId) {
        try {
            validationUtils.validateAppointmentTime(appointment.getAppointmentTime());
            AppointmentDTO appointmentDTO = appointmentService.bookAppointment(appointment, doctorId, patientId);
            ApiResponse<AppointmentDTO> response = new ApiResponse<>("Appointment added successfully", appointmentDTO);
            return ResponseEntity.ok(response);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(new ApiResponse<>(ex.getMessage(), null));
        }
    }


}
