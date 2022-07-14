package com.quiz.hospitalservice.service.impl;

import com.quiz.hospitalservice.dto.request.PatientRequest;
import com.quiz.hospitalservice.dto.request.PatientSearchByDateRequest;
import com.quiz.hospitalservice.dto.response.ResponsePatientData;
import com.quiz.hospitalservice.model.Patient;
import com.quiz.hospitalservice.model.Staff;
import com.quiz.hospitalservice.repository.PatientRepository;
import com.quiz.hospitalservice.repository.StaffRepository;
import com.quiz.hospitalservice.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl  implements PatientService {
    Logger logger = LoggerFactory.getLogger(PatientServiceImpl.class);

  private  final PatientRepository patientRepository;
  private final StaffRepository staffRepository;

    @Override
    public ResponsePatientData add ( Patient request ) {
        Patient patient = patientRepository.save(request);
        ResponsePatientData resp = new ResponsePatientData();
        if (Objects.isNull(patient)) {
            resp.setResponseCode("99");
            resp.setResponseCode("Failed");
        } else {
            resp.setResponseCode("000");
            resp.setResponseMessage("Successful");
            resp.setResult(patient);
        }

        return resp;
    }

    @Override
    public Patient ConvertDtoToModel ( PatientRequest patientRequest ) {
        Patient patient = new Patient();
        patient.setAge(patientRequest.getAge());
        patient.setName(patientRequest.getName());
        patient.setUuid(UUID.randomUUID().toString());

        return patient;
    }

    @Override
    public ResponsePatientData getPatientBy2years ( String staffId ) {
        ResponsePatientData resp = new ResponsePatientData();
        logger.info("UUID {} ",staffId);
        Staff staff = staffRepository.findByUuid(staffId);

        if (staff!=null) {
            List<Patient> data = patientRepository.getListOfPatient2years();
            if (Objects.isNull(data)) {
                resp.setResponseCode("99");
                resp.setResponseMessage("No Data");
            } else {
                resp.setResponseCode("000");
                resp.setResponseMessage("Successful");
                resp.setData(data);

            }
        } else {
            resp.setResponseCode("9xx");
            resp.setResponseMessage("Staff is not authorize");
        }
        return resp;
    }

    @Override
    public List<Patient> findAll () {
       return patientRepository.allPatient();
    }

    @Override
    public Staff validateStaff ( String id ) {
        return staffRepository.findByUuid(id);

    }

    @Override
    public ResponsePatientData deletePatient ( PatientSearchByDateRequest patientSearchByDateRequest ) {
        ResponsePatientData resp = new ResponsePatientData();
        try {

         patientRepository.DeletePatientRecord(patientSearchByDateRequest.getStartDate(), patientSearchByDateRequest.getEndDate());

               resp.setResponseCode("000");
               resp.setResponseMessage("Patient between the date range "+patientSearchByDateRequest.getStartDate()+"and"+ patientSearchByDateRequest.getEndDate()+" has been deleted");

        } catch (Exception ex) {
            logger.info("Exception occurred {} ", ex);
        resp.setResponseCode("9XX");
        resp.setResponseMessage("Exception occurred");
        }
        return resp;

    }

}
