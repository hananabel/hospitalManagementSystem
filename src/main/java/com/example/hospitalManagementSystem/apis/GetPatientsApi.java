package com.example.hospitalManagementSystem.apis;

import com.example.hospitalManagementSystem.dtos.PatientDTO;
import com.example.hospitalManagementSystem.models.Patient;
import com.example.hospitalManagementSystem.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class GetPatientsApi {

    private final PatientService patientService;

    @Autowired
    public GetPatientsApi(PatientService patientService) {
        this.patientService = patientService;
    }

    public List<PatientDTO> getAllPatients() {
        return patientService.getAllPatients();
    }
}
