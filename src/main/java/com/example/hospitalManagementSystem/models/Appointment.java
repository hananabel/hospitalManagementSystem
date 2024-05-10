package com.example.hospitalManagementSystem.models;

import jakarta.persistence.*;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
@Entity
@Table
public class Appointment {


    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    @NotBlank(message = "Appointment time cannot be blank")
    private LocalDateTime appointmentTime;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    public Appointment() {
    }

    public Appointment(LocalDateTime appointmentTime, Doctor doctor, Patient patient) {
        this.appointmentTime = appointmentTime;
        this.doctor = doctor;
        this.patient = patient;
    }
}
