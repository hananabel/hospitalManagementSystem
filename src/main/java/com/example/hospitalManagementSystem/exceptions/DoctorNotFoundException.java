package com.example.hospitalManagementSystem.exceptions;

public class DoctorNotFoundException extends RuntimeException {

    public DoctorNotFoundException(String message) {

        super(message);
    }
}
