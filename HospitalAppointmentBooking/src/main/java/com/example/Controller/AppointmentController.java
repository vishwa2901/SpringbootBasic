package com.example.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Model.Appointment;
import com.example.Service.AppointmentService;

import jakarta.validation.Valid;

@Validated
@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    AppointmentService service;

    // Book appointment
    @PostMapping("/save")
    public Appointment saveAppointment(@Valid @RequestBody Appointment appointment) {
        return service.saveAppointment(appointment);
    }

    // View all appointments
    @GetMapping("/")
    public List<Appointment> getAllAppointments() {
        return service.getAllAppointments();
    }

    // Find appointment by ID
    @GetMapping("/{id}")
    public Appointment getAppointmentById(@PathVariable Long id) {
        return service.getAppointmentById(id);
    }

    // Delete appointment
    @DeleteMapping("/{id}")
    public String deleteAppointment(@PathVariable Long id) {
        return service.deleteAppointment(id);
    }
    

 // Find by doctor name
    @GetMapping("/doctor")
    public List<Appointment> getByDoctorName(@RequestParam String doctorName) {
        return service.getByDoctorName(doctorName);
    }

    // Find by patient name
    @GetMapping("/patient")
    public List<Appointment> getByPatientName(@RequestParam String patientName) {
        return service.getByPatientName(patientName);
    }
}