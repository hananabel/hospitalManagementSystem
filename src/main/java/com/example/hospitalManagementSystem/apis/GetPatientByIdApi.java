package com.example.hospitalManagementSystem.apis;

import com.example.hospitalManagementSystem.dtos.PatientDTO;
import com.example.hospitalManagementSystem.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
public class GetPatientByIdApi {

    private final PatientService patientService;

    @Autowired
    public GetPatientByIdApi(PatientService patientService) {
        this.patientService = patientService;
    }

    public ResponseEntity<PatientDTO> getPatientById(Long id) {
        PatientDTO patientDTO = patientService.getPatientById(id);
        return ResponseEntity.ok(patientDTO);
    }
}
