package com.example.hospitalManagementSystem.apis;

import com.example.hospitalManagementSystem.dtos.PatientDTO;
import com.example.hospitalManagementSystem.models.Patient;
import com.example.hospitalManagementSystem.response.ApiResponse;
import com.example.hospitalManagementSystem.services.PatientService;
import com.example.hospitalManagementSystem.validation.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;

@Controller
public class AddPatientApi {

    private final PatientService patientService;
    private final ValidationUtils validationUtils;

    @Autowired
    public AddPatientApi(PatientService patientService, ValidationUtils validationUtils) {
        this.patientService = patientService;
        this.validationUtils = validationUtils;
    }

    public ResponseEntity<ApiResponse<PatientDTO>> addPatient(Patient patient) {
        try{
            validationUtils.validatePatientInfo(patient);
            validationUtils.validateEmail(patient.getEmail());
            validationUtils.validateAge(patient.getAge());
            PatientDTO patientDTO = patientService.addPatient(patient);
            ApiResponse<PatientDTO> response = new ApiResponse<>("Patient added successfully",patientDTO);
            return ResponseEntity.ok(response);
        } catch(IllegalArgumentException ex){
            return ResponseEntity.badRequest().body(new ApiResponse<>(ex.getMessage(), null));

        }
    }


}
