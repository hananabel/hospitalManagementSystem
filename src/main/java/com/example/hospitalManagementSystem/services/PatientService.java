package com.example.hospitalManagementSystem.services;

import com.example.hospitalManagementSystem.dtos.PatientDTO;

import com.example.hospitalManagementSystem.exceptions.PatientAlreadyExistsException;
import com.example.hospitalManagementSystem.exceptions.PatientNotFoundException;

import com.example.hospitalManagementSystem.models.Appointment;
import com.example.hospitalManagementSystem.models.Patient;
import com.example.hospitalManagementSystem.repositories.AppointmentRepository;
import com.example.hospitalManagementSystem.repositories.PatientRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PatientService {

    private final PatientRepository patientRepository;
    private final ModelMapper modelMapper;
    private final AppointmentRepository appointmentRepository;


    @Autowired
    public PatientService(PatientRepository patientRepository, ModelMapper modelMapper, AppointmentRepository appointmentRepository) {
        this.patientRepository = patientRepository;
        this.modelMapper = modelMapper;
        this.appointmentRepository = appointmentRepository;
    }

    public PatientDTO addPatient(Patient patient) {


        Optional<Patient> patientOptional = patientRepository.findByEmail(patient.getEmail());
        if(patientOptional.isPresent()) {
            throw new PatientAlreadyExistsException("Patient with email " + patient.getEmail() + " already exists");
        }

        patientRepository.save(patient);
        PatientDTO patientDTO = modelMapper.map(patient, PatientDTO.class);
        return patientDTO;

    }

    public List<PatientDTO> getAllPatients() {
        List<Patient> patients = patientRepository.findAll();

        return patients.stream()
                .map(Patient ->modelMapper.map(Patient,PatientDTO.class))
                .collect(Collectors.toList());
    }

    public PatientDTO getPatientById(Long id){
        Optional<Patient> patientOptional = patientRepository.findById(id);
        if(patientOptional.isEmpty()){
            throw new PatientNotFoundException("Patient with id " + id + " not found");
        }
        Patient patient = patientOptional.get();
        PatientDTO patientDTO = modelMapper.map(patient,PatientDTO.class);
        return patientDTO;
    }

    @Transactional
    public PatientDTO updatePatient(Long id,Patient updatedPatient, Long doctorId) {
        Optional<Patient> optionalPatient = patientRepository.findById(id);
        if(optionalPatient.isEmpty()) {
            throw new PatientNotFoundException("Patient with id " + id + " is not found");
        }
        Patient patient = optionalPatient.get();
        if(updatedPatient.getName() != null) {
            patient.setName(updatedPatient.getName());

        }
        if(updatedPatient.getEmail() != null ) {
            Optional<Patient> patientEmail = patientRepository.findByEmail(updatedPatient.getEmail());
            if(patientEmail.isPresent()) {
                throw new PatientAlreadyExistsException("Patient with email " + patient.getEmail() + " already exists");
            }
            patient.setEmail(updatedPatient.getEmail());
        }
        if(updatedPatient.getGender() != null ) {
            patient.setGender(updatedPatient.getGender());
        }
        if(updatedPatient.getPatientCondition() != null ) {
            patient.setPatientCondition(updatedPatient.getPatientCondition());
        }
        if(updatedPatient.getAge() != null && !updatedPatient.getAge() .equals(patient.getAge())) {
            patient.setAge(updatedPatient.getAge() );
        }

        PatientDTO patientDTO = modelMapper.map(patient,PatientDTO.class);
        return patientDTO;


    }

    public void deletePatient(Long id) {
        Optional<Patient> patientOptional = patientRepository.findById(id);
        if (patientOptional.isEmpty()){
            throw new PatientNotFoundException("Patient with id " + id + " is not found");
        }
        List<Appointment> appointmentOptional = appointmentRepository.findByPatientId(id);
        if(!appointmentOptional.isEmpty()){
            appointmentRepository.deleteAll(appointmentOptional);
        }
        patientRepository.deleteById(id);

    }
}
