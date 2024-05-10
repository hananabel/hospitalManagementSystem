package com.example.hospitalManagementSystem.apis;
import com.example.hospitalManagementSystem.dtos.DoctorDTO;
import com.example.hospitalManagementSystem.models.Doctor;
import com.example.hospitalManagementSystem.response.ApiResponse;
import com.example.hospitalManagementSystem.services.DoctorService;
import com.example.hospitalManagementSystem.validation.ValidationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import java.time.LocalDate;

@Controller
public class UpdateDoctorApi {

    private final DoctorService doctorService;
    private final ValidationUtils validationUtils;

    public UpdateDoctorApi(DoctorService doctorService, ValidationUtils validationUtils) {
        this.doctorService = doctorService;
        this.validationUtils = validationUtils;
    }

    public ResponseEntity<ApiResponse<DoctorDTO>> updateDoctor(Long id, Doctor doctor) {
        try {
            validationUtils.validateUpdatedName(doctor.getName());
            validationUtils.validateUpdatedEmail(doctor.getEmail());
            validationUtils.validateUpdatedYear(doctor.getYearOfPractice());

            // Update doctor and retrieve updated DoctorDTO
            DoctorDTO updatedDoctorDTO = doctorService.updateDoctor(id, doctor);

            // Create ApiResponse with message and updated DoctorDTO
            ApiResponse<DoctorDTO> response = new ApiResponse<>("Doctor updated successfully", updatedDoctorDTO);

            // Return ApiResponse with HTTP status 200 OK
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException ex) {
            // Return bad request with error message
            return ResponseEntity.badRequest().body(new ApiResponse<>(ex.getMessage(), null));
        }
    }


}
