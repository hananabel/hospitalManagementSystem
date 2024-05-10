package com.example.hospitalManagementSystem.exceptions;

public class AppointmentNotFoundException extends RuntimeException {

    public AppointmentNotFoundException(String message) {
        super(message);
    }
}
