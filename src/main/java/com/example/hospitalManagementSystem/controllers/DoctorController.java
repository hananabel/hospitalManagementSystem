package com.example.hospitalManagementSystem.controllers;

import com.example.hospitalManagementSystem.apis.*;

import com.example.hospitalManagementSystem.dtos.DoctorDTO;
import com.example.hospitalManagementSystem.models.Doctor;
import com.example.hospitalManagementSystem.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/doctor")
public class DoctorController {

    private final AddDoctorApi addDoctorApi;
    private final GetDoctorsAPI getDoctorsAPI;
    private final UpdateDoctorApi updateDoctorApi;
    private final DeleteDoctorApi deleteDoctorApi;
    private final GetDoctorByIDApi getDoctorByIDApi;

    @Autowired
    public DoctorController(AddDoctorApi addDoctorApi, GetDoctorsAPI getDoctorsAPI, UpdateDoctorApi updateDoctorApi, DeleteDoctorApi deleteDoctorApi, GetDoctorByIDApi getDoctorByIDApi) {
        this.addDoctorApi = addDoctorApi;
        this.getDoctorsAPI = getDoctorsAPI;
        this.updateDoctorApi = updateDoctorApi;
        this.deleteDoctorApi = deleteDoctorApi;
        this.getDoctorByIDApi = getDoctorByIDApi;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<DoctorDTO>> addDoctor(@RequestBody Doctor doctor) {

        return addDoctorApi.addDoctor(doctor);

    }

    @GetMapping("/all")
    public ResponseEntity<List<DoctorDTO>> getAllDoctors() {
        return getDoctorsAPI.getAllDoctors();
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<DoctorDTO> getDoctorById(@PathVariable Long id) {
        return getDoctorByIDApi.getDoctorById(id);
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<ApiResponse<DoctorDTO>> updateDoctor(@PathVariable Long id, @RequestBody Doctor doctor) {
        return updateDoctorApi.updateDoctor(id, doctor);
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<String> deleteDoctor(@PathVariable Long id) {
        return deleteDoctorApi.deleteDoctor(id);
    }

}
