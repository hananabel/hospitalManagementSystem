package com.example.hospitalManagementSystem.exceptions;

public class DoctorAlreadyExistsException extends RuntimeException {

    public DoctorAlreadyExistsException(String message) {

        super(message);
    }
}
