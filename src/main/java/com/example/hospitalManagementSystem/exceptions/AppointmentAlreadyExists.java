package com.example.hospitalManagementSystem.exceptions;

public class AppointmentAlreadyExists extends RuntimeException {
    public AppointmentAlreadyExists(String message) {
        super(message);
    }
}
