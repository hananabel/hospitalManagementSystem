package com.example.hospitalManagementSystem.apis;

import com.example.hospitalManagementSystem.services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
public class DeleteAppointmentApi {

    private final AppointmentService appointmentService;

    @Autowired
    public DeleteAppointmentApi(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    public ResponseEntity<String> deleteAppointment(Long id) {
        if (id != null) {
            appointmentService.deleteAppointment(id);
            return ResponseEntity.status(HttpStatus.OK).body("Appointment deleted successfully");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Request");
    }
}
