package com.example.hospitalManagementSystem.services;

import com.example.hospitalManagementSystem.models.Appointment;
import com.example.hospitalManagementSystem.repositories.AppointmentRepository;
import org.modelmapper.ModelMapper;

import com.example.hospitalManagementSystem.dtos.DoctorDTO;
import com.example.hospitalManagementSystem.exceptions.DoctorAlreadyExistsException;
import com.example.hospitalManagementSystem.exceptions.DoctorNotFoundException;
import com.example.hospitalManagementSystem.models.Doctor;
import com.example.hospitalManagementSystem.models.Patient;
import com.example.hospitalManagementSystem.repositories.DoctorRepository;
import com.example.hospitalManagementSystem.repositories.PatientRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final ModelMapper modelMapper;
    private final AppointmentRepository appointmentRepository;


    @Autowired
    public DoctorService(DoctorRepository doctorRepository, ModelMapper modelMapper, AppointmentRepository appointmentRepository) {
        this.doctorRepository = doctorRepository;
        this.modelMapper = modelMapper;
        this.appointmentRepository = appointmentRepository;
    }

    public DoctorDTO addDoctor(Doctor doctor) {
        Optional<Doctor> doctorOptional = doctorRepository.findByEmail(doctor.getEmail());
        if (doctorOptional.isPresent()) {
            throw new DoctorAlreadyExistsException("Doctor with email " + doctor.getEmail() + " already exists");
        }
        doctorRepository.save(doctor);
        DoctorDTO doctorDTO = modelMapper.map(doctor,DoctorDTO.class);
        return doctorDTO;

    }

    public List<DoctorDTO> getAllDoctors() {
        List<Doctor> doctors = doctorRepository.findAll();
         // Instantiate ModelMapper
        return doctors.stream()
                .map(doctor -> modelMapper.map(doctor, DoctorDTO.class))
                .collect(Collectors.toList());
    }

    public DoctorDTO getDoctorById(Long id) {
        Optional<Doctor> doctorOptional = doctorRepository.findById(id);
        if (doctorOptional.isEmpty()) {
            throw new DoctorNotFoundException("Doctor with id " + id + " not found");
        }
        Doctor doctor = doctorOptional.get();
        DoctorDTO doctorDTO = modelMapper.map(doctor, DoctorDTO.class);
        return doctorDTO;
    }

    @Transactional
    public DoctorDTO updateDoctor(Long id, Doctor updatedDoctor) {
        Optional<Doctor> doctorOptional = doctorRepository.findById(id);
        if (doctorOptional.isEmpty()) {
            throw new DoctorNotFoundException("Doctor with id " + id + " not found");
        }
        Doctor existingDoctor = doctorOptional.get();

        if(updatedDoctor.getName() != null && !existingDoctor.getName().equals(updatedDoctor.getName())) {

            existingDoctor.setName(updatedDoctor.getName());

        }


        if(updatedDoctor.getEmail() != null && !existingDoctor.getEmail().equals(updatedDoctor.getEmail())) {
            Optional<Doctor> doctorWithEmail = doctorRepository.findByEmail(updatedDoctor.getEmail());
            if(doctorWithEmail.isPresent()) {
                throw new DoctorAlreadyExistsException("Doctor with the same email already exists");
            }
            existingDoctor.setEmail(updatedDoctor.getEmail());
        }
        if(updatedDoctor.getYearOfPractice() !=null && !existingDoctor.getYearOfPractice().equals(updatedDoctor.getYearOfPractice())) {
            existingDoctor.setYearOfPractice(updatedDoctor.getYearOfPractice());
        }
        if(updatedDoctor.getSpeciality() != null && !existingDoctor.getSpeciality().equals(updatedDoctor.getSpeciality())) {
            existingDoctor.setSpeciality((updatedDoctor.getSpeciality()));
        }
        if(updatedDoctor.getConsultationFee() != null && !existingDoctor.getConsultationFee().equals((updatedDoctor.getConsultationFee()))) {
            existingDoctor.setConsultationFee((updatedDoctor.getConsultationFee()));
        }

        DoctorDTO doctorDTO = modelMapper.map(existingDoctor, DoctorDTO.class);
        return doctorDTO;
    }

    public void deleteDoctor(Long id) {
        Optional<Doctor> doctorOptional = doctorRepository.findById(id);
        if(doctorOptional.isEmpty()) {
            throw new DoctorNotFoundException("Doctor with id " + id + " not found");
        }
        List<Appointment> appointmentOptional = appointmentRepository.findByDoctorId(id);
        if(!appointmentOptional.isEmpty()){
            appointmentRepository.deleteAll(appointmentOptional);
        }


        doctorRepository.deleteById(id);

    }
}
