package com.example.hospitalManagementSystem.models;

import jakarta.persistence.*;
import lombok.Data;


import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
@Entity
@Table
public class Patient {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    @NotBlank(message = "Name cannot be blank")
    @Column
    private String name;
    @NotBlank(message = "Email cannot be blank")
    @Column//(nullable = false)
    private String email;
    @NotBlank(message = "Gender cannot be blank")
    @Column//(nullable = false)
    private String gender;
    @NotBlank(message = "Patient condition cannot be blank")
    @Column//(nullable = false)
    private String patientCondition;
    @NotBlank(message = "Age cannot be blank")
    @Column//(nullable = false)
    private Integer age;


    public Patient() {
    }





}
