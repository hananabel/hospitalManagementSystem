package com.example.hospitalManagementSystem.controllers;

import com.example.hospitalManagementSystem.apis.*;
import com.example.hospitalManagementSystem.dtos.PatientDTO;
import com.example.hospitalManagementSystem.models.Patient;
import com.example.hospitalManagementSystem.response.ApiResponse;
import com.example.hospitalManagementSystem.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/patient")
public class PatientController {

    private final AddPatientApi addPatientApi;
    private final GetPatientsApi getPatientsApi;
    private final UpdatePatientApi updatePatientApi;
    private final DeletePatientApi deletePatientApi;
    private final GetPatientByIdApi getPatientByIdApi;

    @Autowired
    public PatientController(AddPatientApi addPatientApi,
                             GetPatientsApi getPatientsApi,
                             UpdatePatientApi updatePatientApi,
                             DeletePatientApi deletePatientApi,
                             GetPatientByIdApi getPatientByIdApi) {
        this.addPatientApi = addPatientApi;
        this.getPatientsApi = getPatientsApi;
        this.updatePatientApi = updatePatientApi;
        this.deletePatientApi = deletePatientApi;
        this.getPatientByIdApi = getPatientByIdApi;
    }



    @PostMapping
    public ResponseEntity<ApiResponse<PatientDTO>> addPatient(@RequestBody Patient patient) {
        return addPatientApi.addPatient(patient);

    }

    @GetMapping("/all")
    public List<PatientDTO> getAllPatients() {
        return getPatientsApi.getAllPatients();

    }

    @GetMapping(path = "{id}")
    public ResponseEntity<PatientDTO> getPatientById(@PathVariable Long id) {
        return getPatientByIdApi.getPatientById(id);
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<ApiResponse<PatientDTO>> updatePatient(@PathVariable Long id, @RequestBody Patient patient, @RequestParam(required = false) Long doctorId) {
        return updatePatientApi.updatePatient(id, patient,doctorId);

    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<String> deletePatient(@PathVariable Long id) {
        return deletePatientApi.deletePatient(id);
    }


}
