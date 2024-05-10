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

@Controller
public class UpdateAppointmentApi {
    private final AppointmentService appointmentService;
    private final ValidationUtils validationUtils;

    @Autowired
    public UpdateAppointmentApi(AppointmentService appointmentService, ValidationUtils validationUtils) {
        this.appointmentService = appointmentService;
        this.validationUtils = validationUtils;
    }

    public ResponseEntity<ApiResponse<AppointmentDTO>> updateAppointment(Appointment appointment, Long id, Long doctorId, Long patientId) {
        validationUtils.validateUpdatedAppointmentTime(appointment.getAppointmentTime());
        try{
            appointmentService.updateAppointment(appointment, id, doctorId, patientId);
            AppointmentDTO updatedAppointmentDTO = new AppointmentDTO();
            ApiResponse<AppointmentDTO> response = new ApiResponse<>("Appointment updated successfully", updatedAppointmentDTO);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(new ApiResponse<>(ex.getMessage(), null));
        }
    }
}
