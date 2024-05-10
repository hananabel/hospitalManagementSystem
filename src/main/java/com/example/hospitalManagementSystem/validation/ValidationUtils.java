package com.example.hospitalManagementSystem.validation;

import com.example.hospitalManagementSystem.models.Doctor;
import com.example.hospitalManagementSystem.models.Patient;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
@Validated
public class ValidationUtils {

    public void validateDoctorData(Doctor doctor) {
        // Check if required fields are provided
        if (doctor.getName() == null || doctor.getName().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        if(doctor.getEmail() == null || doctor.getEmail().isEmpty()){
            throw new IllegalArgumentException("Email cannot be empty");
        }
        if(doctor.getSpeciality() == null || doctor.getSpeciality().isEmpty()){
            throw new IllegalArgumentException("Speciality cannot be empty");
        }
        if(doctor.getYearOfPractice() == null ){
            throw new IllegalArgumentException("Year of practice cannot be empty");
        }
        if(doctor.getConsultationFee() == null){
            throw new IllegalArgumentException("Consultation fee cannot be empty");
        }

    }

    public void validateEmail(String email) {
        // Simple regex for email validation
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        if (!email.matches(emailRegex)) {
            throw new IllegalArgumentException("Invalid email format");
        }
    }

    public void validateYear(LocalDate yearOfPractice) {
        if(yearOfPractice.isAfter(LocalDate.now())){
            throw new IllegalArgumentException("Year of practice cannot be after now");
        }
    }

    public void validatePatientInfo(Patient patient) {
        if(patient.getName() == null || patient.getName().isEmpty()){
            throw new IllegalArgumentException("Name cannot be empty");
        }
        if(patient.getEmail() == null || patient.getEmail().isEmpty()){
            throw new IllegalArgumentException("Email cannot be empty");
        }
        if(patient.getGender() == null || patient.getGender().isEmpty()){
            throw new IllegalArgumentException("Gender cannot be empty");
        }
        if(patient.getPatientCondition() == null || patient.getPatientCondition().isEmpty()){
            throw new IllegalArgumentException("Patient condition cannot be empty");
        }
        if(patient.getAge() == null){}


    }

    public void validateAge(Integer age) {
        if(age < 0){
            throw new IllegalArgumentException("Age cannot be negative");
        }
    }

    public void validateAppointmentTime(LocalDateTime appointmentTime) {
        if (appointmentTime.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Appointment time cannot be before current date");
        }
        if (appointmentTime == null) {
            throw new IllegalArgumentException("Appointment time cannot be null");
        }
        if(appointmentTime.getHour()<8 || appointmentTime.getHour()>20){
            throw new IllegalArgumentException("Doctor is only available between 8am - 9pm");
        }
        if (appointmentTime.getMinute() % 15 != 0) {
            throw new IllegalArgumentException("Appointment time must be at 15 minute interval");
        }
    }



    public void validateUpdatedName(String name) {
        if(name != null) {
            if(name.isEmpty()){
                throw new IllegalArgumentException("Please provide valid name");            }
        }
    }

    public void validateUpdatedEmail(String email) {
        // Simple regex for email validation
        if(email != null){
            String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
            if (!email.matches(emailRegex)) {
                throw new IllegalArgumentException("Invalid email format");
            }
        }
    }

    public void validateUpdatedYear(LocalDate yearOfPractice) {
        if(yearOfPractice != null) {
            if(yearOfPractice.isAfter(LocalDate.now())){
                throw new IllegalArgumentException("Year of practice cannot be after now");
            }
        }
    }


    public void validateUpdatedAppointmentTime(LocalDateTime appointmentTime) {

        if (appointmentTime != null) {
            if (appointmentTime.isBefore(LocalDateTime.now())) {
                throw new IllegalArgumentException("Appointment time cannot be before current date");
            }
            if (appointmentTime.getHour() < 8 || appointmentTime.getHour() > 20) {
                throw new IllegalArgumentException("Doctor is only available between 8am - 9pm");
            }
            if (appointmentTime.getMinute() % 15 != 0) {
                throw new IllegalArgumentException("Appointment time must be at 15 minute interval");
            }
        }
    }
}
