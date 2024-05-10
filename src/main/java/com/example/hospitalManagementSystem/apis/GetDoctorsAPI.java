package com.example.hospitalManagementSystem.apis;

import com.example.hospitalManagementSystem.dtos.DoctorDTO;
import com.example.hospitalManagementSystem.services.DoctorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class GetDoctorsAPI {

    private final DoctorService doctorService;

    public GetDoctorsAPI(DoctorService doctorService) {
        this.doctorService = doctorService;
    }



    public ResponseEntity<List<DoctorDTO>> getAllDoctors() {
        return ResponseEntity.status(HttpStatus.OK).body(doctorService.getAllDoctors());
    }
}
