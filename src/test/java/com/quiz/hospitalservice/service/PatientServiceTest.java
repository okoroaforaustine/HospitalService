package com.quiz.hospitalservice.service;

import com.quiz.hospitalservice.HospitalServiceApplication;
import com.quiz.hospitalservice.dto.response.ResponsePatientData;
import com.quiz.hospitalservice.model.Patient;
import com.quiz.hospitalservice.model.Staff;
import com.quiz.hospitalservice.repository.PatientRepository;
import com.quiz.hospitalservice.repository.StaffRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@AutoConfigureMockMvc
@TestPropertySource("/application-dev.properties")
@Transactional
@SpringBootTest(classes = HospitalServiceApplication.class)
public class PatientServiceTest {

    @Autowired
    PatientService patientService;

    @Mock
    PatientRepository patientRepository;

   @Autowired
    StaffRepository staffRepository;

    @Test
   public void addPatient_when_StaffUUID_is_valid() {
        Staff staff = new Staff();
        staff.setUuid(UUID.randomUUID().toString());
        staff.setName("patient okon");
        staff.setRegistration_date(LocalDate.now());
        Staff staffObj = staffRepository.save(staff);
        Staff getStaff = staffRepository.findByUuid(staffObj.getUuid());
        Patient patient = new Patient();
        patient.setAge("2");
        patient.setUuid(UUID.randomUUID().toString());
        patient.setName("patient austine");
        patient.setLast_vist_date(LocalDate.now());
        Staff validateStaffObj = patientService.validateStaff(getStaff.getUuid());
        assertNotNull(validateStaffObj.getUuid());
        ResponsePatientData actual = patientService.add(patient);
        Assert.assertEquals(actual.getResult(), patient);

    }

    public void dont_addPatient_when_StaffUUID_is_not_valid() {
        Staff staff = new Staff();
        staff.setUuid(UUID.randomUUID().toString());
        staff.setName("patient okon");
        staff.setRegistration_date(LocalDate.now());
        Staff staffObj = staffRepository.save(staff);
        Staff getStaff = staffRepository.findByUuid(staffObj.getUuid());
        Patient patient = new Patient();
        patient.setAge("2");
        patient.setUuid("duddjjdddjdjd");
        patient.setName("patient austine");
        patient.setLast_vist_date(LocalDate.now());
        Staff validateStaffObj = patientService.validateStaff(getStaff.getUuid());
        assertNull(validateStaffObj.getUuid());
        ResponsePatientData actual = patientService.add(patient);
        Assert.assertNotEquals(actual.getResult(), patient);

    }




}


