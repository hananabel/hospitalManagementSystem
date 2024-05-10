package com.example.hospitalManagementSystem.services;

import com.example.hospitalManagementSystem.dtos.AppointmentDTO;
import com.example.hospitalManagementSystem.dtos.DoctorDTO;
import com.example.hospitalManagementSystem.exceptions.*;
import com.example.hospitalManagementSystem.models.Appointment;
import com.example.hospitalManagementSystem.models.Doctor;
import com.example.hospitalManagementSystem.models.Patient;
import com.example.hospitalManagementSystem.repositories.AppointmentRepository;
import com.example.hospitalManagementSystem.repositories.DoctorRepository;
import com.example.hospitalManagementSystem.repositories.PatientRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public AppointmentService(AppointmentRepository appointmentRepository, DoctorRepository doctorRepository, PatientRepository patientRepository, ModelMapper modelMapper) {
        this.appointmentRepository = appointmentRepository;
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
        this.modelMapper = modelMapper;

        modelMapper.addMappings(new PropertyMap<Appointment, AppointmentDTO>() {
            @Override
            protected void configure() {
                map().setDoctorName(source.getDoctor().getName());
                map().setPatientName(source.getPatient().getName());
            }
        });
    }

    public AppointmentDTO bookAppointment(Appointment appointment, Long doctorId, Long patientId) {
        Optional<Doctor> doctorOptional = doctorRepository.findById(doctorId);
        if(doctorOptional.isEmpty()) {
            throw new DoctorNotFoundException("Doctor with id "+ doctorId +" is not found" ); // Doctor not found
        }


        Optional<Patient> patientOptional = patientRepository.findById(patientId);
        if(patientOptional.isEmpty()) {
            throw new PatientNotFoundException("Patient with id "+ patientId +" is not found" ); // Doctor not found
        }

        List<Appointment> conflictingDoctorAppointment = appointmentRepository.findByDoctorIdAndAppointmentTime(doctorId, appointment.getAppointmentTime());
        List<Appointment> doctorAppointment = appointmentRepository.findByDoctorId(doctorId);
        if(!conflictingDoctorAppointment.isEmpty()) {
            if (!doctorAppointment.isEmpty()) {
                StringBuilder messageBuilder = new StringBuilder("Doctor already has appointments at the following times: ");
                for (Appointment allAppointment : doctorAppointment) {
                    messageBuilder.append(allAppointment.getAppointmentTime()).append(", ");
                }
                String message = messageBuilder.substring(0, messageBuilder.length() - 2); // Remove the trailing comma and space
                throw new DoctorHasAppointmentException(message);
            }

        }

        List<Appointment> conflictingPatientAppointment = appointmentRepository.findByPatientIdAndAppointmentTime(patientId, appointment.getAppointmentTime());
        List<Appointment>patientAppointment = appointmentRepository.findByPatientId(patientId);
        if(!conflictingPatientAppointment.isEmpty()) {
            if(!patientAppointment.isEmpty()) {
                StringBuilder messageBuilder = new StringBuilder("Patient already has an appointment at the following times: ");
                for(Appointment allAppointment : patientAppointment) {
                    messageBuilder.append(allAppointment.getAppointmentTime()).append(", ");
                }
                String message = messageBuilder.substring(0, messageBuilder.length() - 2);
                throw new PatientHasAppointmentException(message);
            }

        }

        Doctor doctor = doctorOptional.get();
        appointment.setDoctor(doctor);
        Patient patient = patientOptional.get();
        appointment.setPatient(patient);

        appointmentRepository.save(appointment);
        AppointmentDTO appointmentDTO = modelMapper.map(appointment,AppointmentDTO.class);
        return appointmentDTO;
    }

    public List<AppointmentDTO> getAllAppointments() {
        List<Appointment> appointments = appointmentRepository.findAll();
        return appointments.stream()
                .map(appointment -> modelMapper.map(appointment, AppointmentDTO.class))
                .collect(Collectors.toList());
    }

    public AppointmentDTO getAppointmentById(Long id) {
        Optional<Appointment> appointmentOptional = appointmentRepository.findById(id);
        if(appointmentOptional.isEmpty()){
            throw new AppointmentNotFoundException("Appointment with id " +id + " is not found");
        }
        Appointment appointment = appointmentOptional.get();
        AppointmentDTO appointmentDTO = modelMapper.map(appointment, AppointmentDTO.class);
        return appointmentDTO;
    }

    @Transactional
    public AppointmentDTO updateAppointment(Appointment updatedAppointment, Long appointmentId,  Long doctorId, Long patientId) {
        Optional<Appointment> appointmentOptional = appointmentRepository.findById(appointmentId);
        if(appointmentOptional.isEmpty()) {
            throw new AppointmentNotFoundException("Appointment with id " + appointmentId + " not found");
        }
        Appointment appointment = appointmentOptional.get();
        if(doctorId != null) {
            Optional<Doctor> doctorOptional = doctorRepository.findById(doctorId);
            if(doctorOptional.isEmpty()) {
                throw new DoctorNotFoundException("Doctor with id "+ doctorId +" is not found" ); // Doctor not found
            }
            List<Appointment> conflictingDoctorAppointment = appointmentRepository.findByDoctorIdAndAppointmentTime(doctorId, appointment.getAppointmentTime());
            if(!conflictingDoctorAppointment.isEmpty()) {
                throw new DoctorHasAppointmentException("Doctor already has an appointment at " + appointment.getAppointmentTime()); // Doctor has another appointment at the same time
            }
            appointment.setDoctor(doctorOptional.get());
        }

        if(patientId != null) {
            Optional<Patient> patientOptional = patientRepository.findById(patientId);
            if(patientOptional.isEmpty()) {
                throw new PatientNotFoundException("Patient with id "+ patientId +" is not found" );
            }

            List<Appointment> conflictingPatientAppointment = appointmentRepository.findByPatientIdAndAppointmentTime(patientId, appointment.getAppointmentTime());
            if(!conflictingPatientAppointment.isEmpty()) {
                throw new PatientHasAppointmentException("Patient already has an appointment at " + appointment.getAppointmentTime());
            }
            appointment.setPatient(patientOptional.get());
        }

        if(updatedAppointment.getAppointmentTime() != null) {
            Optional<Appointment> appointmentOptional1 = appointmentRepository.findByPatientIdAndDoctorIdAndAppointmentTime(patientId,doctorId,updatedAppointment.getAppointmentTime());
            if(appointmentOptional1.isPresent()) {
                throw new AppointmentAlreadyExists("Cannot book appointment. Choose another time");
            }
            appointment.setAppointmentTime(updatedAppointment.getAppointmentTime());
        }
        AppointmentDTO appointmentDTO = modelMapper.map(appointment, AppointmentDTO.class);
        return appointmentDTO;
    }

    public void deleteAppointment(Long id) {
        Optional<Appointment> appointmentOptional = appointmentRepository.findById(id);
        if(appointmentOptional.isEmpty()) {
            throw new AppointmentNotFoundException("Appointment with id " + id + " not found");
        }
    }
}
