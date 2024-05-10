package com.example.hospitalManagementSystem.repositories;

import com.example.hospitalManagementSystem.dtos.DoctorDTO;
import com.example.hospitalManagementSystem.models.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    Optional<Doctor> findByEmail(String email);


//    @Query("SELECT new com.example.hospitalManagementSystem.dtos.DoctorDTO(d.name, d.email, d.speciality, d.consultationFee) FROM Doctor d")
//    List<DoctorDTO> findAllWithoutIdAndYearOfExperience();
}
