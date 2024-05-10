package com.example.hospitalManagementSystem.apis;

import com.example.hospitalManagementSystem.dtos.DoctorDTO;
import com.example.hospitalManagementSystem.models.Doctor;
import com.example.hospitalManagementSystem.response.ApiResponse;
import com.example.hospitalManagementSystem.services.DoctorService;
import com.example.hospitalManagementSystem.validation.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;


@Controller
public class AddDoctorApi {

    private final DoctorService doctorService;
    private final ValidationUtils validationUtils;

    @Autowired
    public AddDoctorApi(DoctorService doctorService, ValidationUtils validationUtils) {
        this.doctorService = doctorService;
        this.validationUtils = validationUtils;
    }

    public ResponseEntity<ApiResponse<DoctorDTO>> addDoctor(Doctor doctor) {

        try{
            validationUtils.validateDoctorData(doctor);
            validationUtils.validateEmail(doctor.getEmail());
            validationUtils.validateYear(doctor.getYearOfPractice());
            DoctorDTO doctorDTO = doctorService.addDoctor(doctor);
            ApiResponse<DoctorDTO> response = new ApiResponse<>("Doctor added successfully", doctorDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception ex){
            return ResponseEntity.badRequest().body(new ApiResponse<>(ex.getMessage(), null));
        }

    }


}
