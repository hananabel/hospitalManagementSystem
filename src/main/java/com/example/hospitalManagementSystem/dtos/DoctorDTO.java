package com.example.hospitalManagementSystem.dtos;

import jakarta.persistence.Transient;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class DoctorDTO {
    private String name;
    private String email;
    private String speciality;
    private Integer experience;
    private BigDecimal consultationFee;






}

