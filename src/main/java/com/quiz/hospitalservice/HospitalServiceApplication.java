package com.quiz.hospitalservice;

import com.quiz.hospitalservice.model.Patient;
import com.quiz.hospitalservice.repository.PatientRepository;
import com.quiz.hospitalservice.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@SpringBootApplication
public class HospitalServiceApplication  implements  CommandLineRunner {

    @Autowired
   PatientRepository patientRepository;

    public static void main ( String[] args ) {
        SpringApplication.run(HospitalServiceApplication.class, args);
    }
    @Override
    public void run(String... args) throws Exception
    {
        Patient  patient= new Patient();
        patient.setAge("2");
        patient.setName("Tony louis");
        patient.setUuid(UUID.randomUUID().toString());
        patient.setLast_vist_date(LocalDate.now());
        patientRepository.save(patient);
        Patient  patient1= new Patient();
        patient1.setAge("2");
        patient1.setName("Austine okoroafor");
        patient1.setUuid(UUID.randomUUID().toString());
        patient1.setLast_vist_date(LocalDate.now());
        patientRepository.save(patient1);




    }


}
