package com.example.hospitalManagementSystem.apis;

import com.example.hospitalManagementSystem.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
public class DeletePatientApi {

    private final PatientService patientService;

    @Autowired
    public DeletePatientApi(PatientService patientService) {
        this.patientService = patientService;
    }

    public ResponseEntity<String> deletePatient(Long id) {
        if(id != null){
            patientService.deletePatient(id);
            return ResponseEntity.ok("Patient deleted successfully");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Request");
    }
}
