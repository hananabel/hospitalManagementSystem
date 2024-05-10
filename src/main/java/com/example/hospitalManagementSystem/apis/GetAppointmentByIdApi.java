package com.example.hospitalManagementSystem.apis;

import com.example.hospitalManagementSystem.dtos.AppointmentDTO;
import com.example.hospitalManagementSystem.services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
public class GetAppointmentByIdApi {

    private final AppointmentService appointmentService;

    @Autowired
    public GetAppointmentByIdApi(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    public ResponseEntity<AppointmentDTO> getAppointmentById(Long id) {
        return ResponseEntity.ok(appointmentService.getAppointmentById(id));
    }
}
