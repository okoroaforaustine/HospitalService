package com.quiz.hospitalservice.controller;

import com.quiz.hospitalservice.dto.request.PatientRequest;

import com.quiz.hospitalservice.dto.request.PatientSearchByDateRequest;
import com.quiz.hospitalservice.dto.request.StaffRequest;
import com.quiz.hospitalservice.dto.response.ResponsePatientData;

import com.quiz.hospitalservice.model.Patient;
import com.quiz.hospitalservice.model.Staff;
import com.quiz.hospitalservice.service.PatientService;
import com.quiz.hospitalservice.util.CsvService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.core.io.Resource;

@RestController
@RequestMapping(value = "/api/v1/patient")
@RequiredArgsConstructor
public class PatientController {
    Logger logger = LoggerFactory.getLogger(PatientController.class);
    private final PatientService patientService;
    private final CsvService fileService;
    @PostMapping(value = "/add")
    public ResponseEntity<?> AddPatient( @RequestBody PatientRequest request){
        ResponsePatientData response=new  ResponsePatientData ();
        logger.info("creating Patient Record {} ",request);
        Patient patient=   patientService.ConvertDtoToModel(request);
        response = patientService.add(patient);
        return  ResponseEntity.ok().body(response);

    }
    @PostMapping  ( value = "/getallPatientbytwoyears")
    public ResponseEntity<?> getPatient(@RequestBody StaffRequest staffUUID){
        ResponsePatientData response=new      ResponsePatientData ();
        response = patientService.getPatientBy2years(staffUUID.getUuid());
        return  ResponseEntity.ok().body(response);

    }

    @PostMapping  (value = "/downloadpatient")
    public ResponseEntity<Resource> getdownload(@RequestBody StaffRequest staffUUID) {
        String filename = "patient.csv";
        logger.info("entered here");
        Staff Staffvalidate = patientService.validateStaff(staffUUID.getUuid());
        if (Staffvalidate!=null) {
            InputStreamResource file = new InputStreamResource(fileService.load());
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                    .contentType(MediaType.parseMediaType("application/csv"))
                    .body(file);
        }else{

            return ResponseEntity.ok().build();
        }
    }

    @PostMapping  ( value = "/delete")
    public ResponseEntity<ResponsePatientData> getPatient( @RequestBody PatientSearchByDateRequest patientSearchByDateRequest) {
        ResponsePatientData response = new ResponsePatientData();
        Staff staffValidate = patientService.validateStaff(patientSearchByDateRequest.getStaffId());
        if (staffValidate!=null) {
            response = patientService.deletePatient(patientSearchByDateRequest);
            return ResponseEntity.ok().body(response);
        } else {
            response.setResponseCode("99");
            response.setResponseMessage("Staff not authorized ");
            return ResponseEntity.badRequest().body(response);
        }
    }
}
