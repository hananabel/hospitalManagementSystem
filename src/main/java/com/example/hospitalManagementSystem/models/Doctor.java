package com.example.hospitalManagementSystem.models;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotBlank;

@Data
@Entity
@Table
public class Doctor {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    @NotBlank(message = "Name cannot be blank")
    @Column//(nullable = false)
    private String name;
    @NotBlank(message = "email cannot be blank")
    @Column//(unique = true, nullable = false)
    private String email;
    @NotBlank(message = "Speciality cannot be blank")
    @Column//(nullable = false)
    private String speciality;
    @NotBlank(message = "Year of practice cannot be blank")
    @Column//(nullable = false)
    private LocalDate yearOfPractice;
    @Transient
    private Integer experience;
    @NotBlank(message = "Consultation fee cannot be blank")
    @Column//(nullable = false)
    private BigDecimal consultationFee;


    public Doctor() {
    }


    public Integer getExperience() {
        return Period.between(this.yearOfPractice, LocalDate.now()).getYears();
    }


}

