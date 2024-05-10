package com.example.hospitalManagementSystem.repositories;

import com.example.hospitalManagementSystem.models.Appointment;
import com.example.hospitalManagementSystem.models.Doctor;
import com.example.hospitalManagementSystem.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    List<Appointment> findByDoctorIdAndAppointmentTime(Long doctorId, LocalDateTime appointmentTime);

    List<Appointment> findByPatientIdAndAppointmentTime(Long patientId, LocalDateTime appointmentTime);

    Optional<Appointment> findByPatientIdAndDoctorIdAndAppointmentTime(Long patientId, Long doctorId, LocalDateTime appointmentTime);

    List<Appointment> findByDoctorId(Long doctorId);

    List<Appointment> findByPatientId(Long patientId);
}
