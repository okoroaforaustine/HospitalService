package com.quiz.hospitalservice.service;

import com.quiz.hospitalservice.dto.request.PatientRequest;
import com.quiz.hospitalservice.dto.request.PatientSearchByDateRequest;
import com.quiz.hospitalservice.dto.response.ResponsePatientData;
import com.quiz.hospitalservice.model.Patient;
import com.quiz.hospitalservice.model.Staff;
import org.springframework.stereotype.Service;

import java.util.List;


public interface PatientService {
    ResponsePatientData add( Patient request );
    Patient ConvertDtoToModel( PatientRequest patientRequest);
    ResponsePatientData getPatientBy2years(String StaffId);
    List<Patient>findAll();
    Staff validateStaff( String id);

    ResponsePatientData deletePatient( PatientSearchByDateRequest patientSearchByDateRequest);

}
