package com.example.hospitalManagementSystem.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DoctorNotFoundException.class)
    public ResponseEntity<String> handleDoctorNotFoundException(DoctorNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(DoctorAlreadyExistsException.class)
    public ResponseEntity<String> handleDoctorAlreadyExistsException(DoctorAlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    @ExceptionHandler(DoctorHasAppointmentException.class)
    public ResponseEntity<String> handleDoctorHasAppointmentException(DoctorHasAppointmentException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    @ExceptionHandler(PatientAlreadyExistsException.class)
    public ResponseEntity<String> handlePatientAlreadyExistsException(PatientAlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    @ExceptionHandler(PatientNotFoundException.class)
    public ResponseEntity<String> handlePatientNotFoundException(PatientNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(PatientHasAppointmentException.class)
    public ResponseEntity<String> handlePatientHasAppointmentException(PatientHasAppointmentException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    @ExceptionHandler(AppointmentNotFoundException.class)
    public ResponseEntity<String> handleAppointmentNotFoundException(AppointmentNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(AppointmentAlreadyExists.class)
    public ResponseEntity<String> handleAppointmentAlreadyExists(AppointmentAlreadyExists ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred: " + ex.getMessage());
    }

    // Add more exception handler methods as needed
}
