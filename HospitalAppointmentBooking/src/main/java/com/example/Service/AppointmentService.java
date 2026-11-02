package com.example.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Model.Appointment;
import com.example.Repository.AppointmentRepository;
import com.example.Exception.AppointmentNotFoundException;

@Service
public class AppointmentService {

    @Autowired
    AppointmentRepository repo;

    // Book appointment
    public Appointment saveAppointment(Appointment appointment) {
        return repo.save(appointment);
    }

    // View all appointments
    public List<Appointment> getAllAppointments() {
        return repo.findAll();
    }

    // Find appointment by ID
    public Appointment getAppointmentById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new AppointmentNotFoundException(
                        "Appointment not found with ID: " + id));
    }

    // Delete appointment
    public String deleteAppointment(Long id) {

        Appointment appointment = repo.findById(id)
                .orElseThrow(() -> new AppointmentNotFoundException(
                        "Appointment not found with ID: " + id));

        repo.delete(appointment);

        return "Appointment deleted successfully";
    }
    
    // find by docter name
    public List<Appointment> getByDoctorName(String doctorName) {
        return repo.findByDoctorName(doctorName);
    }

    // find by patient name
    public List<Appointment> getByPatientName(String patientName) {
        return repo.findByPatientName(patientName);
    }
}