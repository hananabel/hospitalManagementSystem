package com.example.hospitalManagementSystem.repositories;

import com.example.hospitalManagementSystem.dtos.PatientDTO;
import com.example.hospitalManagementSystem.models.Doctor;
import com.example.hospitalManagementSystem.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {


    Optional<Patient> findByEmail(String email);



    @Query("SELECT new com.example.hospitalManagementSystem.dtos.PatientDTO(p.name, p.email, p.gender, p.age, p.patientCondition) FROM Patient p")
    List<PatientDTO> findADetails();

}
