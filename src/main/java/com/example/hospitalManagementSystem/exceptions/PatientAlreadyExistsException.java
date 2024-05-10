package com.example.hospitalManagementSystem.exceptions;

public class PatientAlreadyExistsException extends RuntimeException {

    public PatientAlreadyExistsException(String message) {

        super(message);
    }
}
