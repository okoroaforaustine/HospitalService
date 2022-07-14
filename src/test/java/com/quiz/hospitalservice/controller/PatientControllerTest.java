package com.quiz.hospitalservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.quiz.hospitalservice.HospitalServiceApplication;
import com.quiz.hospitalservice.model.Patient;
import com.quiz.hospitalservice.model.Staff;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.UUID;

import static org.hamcrest.Matchers.endsWith;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@TestPropertySource("/application-dev.properties")
@Transactional
@SpringBootTest(classes = HospitalServiceApplication.class)
public class PatientControllerTest {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private static MockHttpServletRequest request;

    @Autowired
    private MockMvc mockMvc;


    @Autowired
    private ObjectMapper objectMapper;



    @Test
    public void getPatientHttpRequest() throws Exception{
        Patient patient=new Patient();
        patient.setName("Low patient");
        patient.setUuid(UUID.randomUUID().toString());
        patient.setLast_vist_date(LocalDate.now());
        patient.setAge("2");
        mockMvc.perform(post("/api/v1/patient/add").
                        contentType(MediaType.APPLICATION_JSON).
                        content(objectMapper.writeValueAsString(patient))).
                andExpect(status().isOk())
                .andExpect( jsonPath("$.result.name",endsWith("patient")));

    }

    @BeforeEach
    public void setUpDatabase(){
        String uuid= UUID.randomUUID().toString();
        String insertStaff="INSERT INTO STAFF(uuid,name,registration_date)VALUES('"+uuid+"','staff austine','"+ LocalDate.now()+"')";
        jdbcTemplate.execute(insertStaff);
        String insertPateint="INSERT INTO PATIENT(uuid,name,age,last_vist_date)VALUES('"+uuid+"','patient okoroafor','2','"+LocalDate.now()+"')";
        jdbcTemplate
                .execute(insertPateint);
    }


    @AfterEach
    public void cleanDatabase(){
        String deleteStaff="delete  from patient where name='staff austine'";
        jdbcTemplate.execute(deleteStaff);
        String deletePatient="delete  from patient where name='patient okoroafor'";
        jdbcTemplate.execute(deletePatient);

    }
}
