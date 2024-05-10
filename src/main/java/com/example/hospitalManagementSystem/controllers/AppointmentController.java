package com.example.hospitalManagementSystem.controllers;

import com.example.hospitalManagementSystem.apis.*;
import com.example.hospitalManagementSystem.dtos.AppointmentDTO;
import com.example.hospitalManagementSystem.models.Appointment;
import com.example.hospitalManagementSystem.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping(path = "api/v1/appointment")
public class AppointmentController {
    private final BookAppointmentApi bookAppointmentApi;
    private final GetAppointmentsApi getAppointmentsApi;
    private final UpdateAppointmentApi updateAppointmentApi;
    private final DeleteAppointmentApi deleteAppointmentApi;
    private final GetAppointmentByIdApi getAppointmentByIdApi;

    @Autowired
    public AppointmentController(BookAppointmentApi bookAppointmentApi, GetAppointmentsApi getAppointmentsApi, UpdateAppointmentApi updateAppointmentApi, DeleteAppointmentApi deleteAppointmentApi, GetAppointmentByIdApi getAppointmentByIdApi) {
        this.bookAppointmentApi = bookAppointmentApi;
        this.getAppointmentsApi = getAppointmentsApi;
        this.updateAppointmentApi = updateAppointmentApi;
        this.deleteAppointmentApi = deleteAppointmentApi;
        this.getAppointmentByIdApi = getAppointmentByIdApi;
    }

    @PostMapping(path = "{doctorId}/{patientId}")
    public ResponseEntity<ApiResponse<AppointmentDTO>> bookAppointment(@RequestBody Appointment appointment, @PathVariable Long doctorId, @PathVariable Long patientId) {
        return bookAppointmentApi.bookAppointment(appointment,doctorId,patientId);
    }

    @GetMapping("/all")
    public ResponseEntity<List<AppointmentDTO>> getAppointments() {
        return getAppointmentsApi.getAllAppointments();
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<AppointmentDTO> getAppointmentById(@PathVariable Long id) {
        return getAppointmentByIdApi.getAppointmentById(id);
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<ApiResponse<AppointmentDTO>> updateAppointment(@RequestBody Appointment appointment, @PathVariable Long id, @RequestParam(required = false) Long doctorId, @RequestParam(required = false) Long patientId) {
        return updateAppointmentApi.updateAppointment(appointment,id,doctorId,patientId);
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<String> deleteAppointment(@PathVariable Long id) {
        return deleteAppointmentApi.deleteAppointment(id);
    }

}
