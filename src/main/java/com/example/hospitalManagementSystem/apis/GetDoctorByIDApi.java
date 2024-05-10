package com.example.hospitalManagementSystem.apis;

import com.example.hospitalManagementSystem.dtos.DoctorDTO;
import com.example.hospitalManagementSystem.services.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
public class GetDoctorByIDApi {

    private final DoctorService doctorService;

    @Autowired
    public GetDoctorByIDApi(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    public ResponseEntity<DoctorDTO> getDoctorById(Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(doctorService.getDoctorById(id));
    }
}
