package com.example.hospitalManagementSystem.exceptions;

public class PatientNotFoundException extends RuntimeException {

    public PatientNotFoundException( String message) {

        super(message);
    }
}
