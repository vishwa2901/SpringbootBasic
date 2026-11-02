package com.example.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Model.Appointment;

@Repository
public interface AppointmentRepository
        extends JpaRepository<Appointment, Long> {

	List<Appointment> findByDoctorName(String doctorName);

    List<Appointment> findByPatientName(String patientName);
}