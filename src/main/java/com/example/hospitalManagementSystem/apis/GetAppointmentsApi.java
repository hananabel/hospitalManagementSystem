package com.example.hospitalManagementSystem.apis;

import com.example.hospitalManagementSystem.dtos.AppointmentDTO;
import com.example.hospitalManagementSystem.models.Appointment;
import com.example.hospitalManagementSystem.services.AppointmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.List;


@Controller
public class GetAppointmentsApi {

    private final AppointmentService appointmentService;

    public GetAppointmentsApi(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    public ResponseEntity<List<AppointmentDTO>> getAllAppointments() {
        return ResponseEntity.status(HttpStatus.OK).body(appointmentService.getAllAppointments());
    }
}
