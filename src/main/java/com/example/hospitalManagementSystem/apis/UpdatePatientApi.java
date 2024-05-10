package com.example.hospitalManagementSystem.apis;

import com.example.hospitalManagementSystem.dtos.DoctorDTO;
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
public class UpdatePatientApi {

    private final PatientService patientService;
    private final ValidationUtils validationUtils;

    @Autowired
    public UpdatePatientApi(PatientService patientService, ValidationUtils validationUtils) {
        this.patientService = patientService;
        this.validationUtils = validationUtils;
    }

    public ResponseEntity<ApiResponse<PatientDTO>> updatePatient(Long id, Patient patient, Long doctorId) {
        try{
            validationUtils.validateUpdatedName(patient.getName());
            validationUtils.validateUpdatedEmail(patient.getEmail());

            PatientDTO updatedPatientDTO = patientService.updatePatient(id, patient,doctorId);
            ApiResponse<PatientDTO> response = new ApiResponse<>("Patient updated successfully", updatedPatientDTO);
            return ResponseEntity.ok(response);
        } catch(IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(new ApiResponse<>(ex.getMessage(), null));
        }
    }


}
