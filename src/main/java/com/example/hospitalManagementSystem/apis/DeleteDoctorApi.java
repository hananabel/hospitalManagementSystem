package com.example.hospitalManagementSystem.apis;

import com.example.hospitalManagementSystem.services.DoctorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
public class DeleteDoctorApi {

    private final DoctorService doctorService;

    public DeleteDoctorApi(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    public ResponseEntity<String> deleteDoctor(Long id) {

        if(id != null) {
            doctorService.deleteDoctor(id);
            return ResponseEntity.status(HttpStatus.OK).body("Doctor deleted successfully");
        } else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Request");
        }

    }
}
