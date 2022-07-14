package com.quiz.hospitalservice.service;

import com.quiz.hospitalservice.HospitalServiceApplication;
import com.quiz.hospitalservice.dto.response.ResponsePatientData;
import com.quiz.hospitalservice.dto.response.ResponseStaffData;
import com.quiz.hospitalservice.model.Staff;
import com.quiz.hospitalservice.repository.StaffRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.UUID;

@AutoConfigureMockMvc
@TestPropertySource("/application-dev.properties")
@Transactional
@SpringBootTest(classes = HospitalServiceApplication.class)
public class StaffServiceTest {

    @Autowired

    StaffService staffService;

    @Test
    public void should_addStaff(){
        Staff staff = new Staff();
        staff.setUuid(UUID.randomUUID().toString());
        staff.setName("nurse okon");
        staff.setRegistration_date(LocalDate.now());
        ResponseStaffData actual = staffService.add(staff);
        Assert.assertEquals(actual.getResult(), staff);
    }
}
