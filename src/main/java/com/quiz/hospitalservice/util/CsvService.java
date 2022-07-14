package com.quiz.hospitalservice.util;

import com.quiz.hospitalservice.model.Patient;
import com.quiz.hospitalservice.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CsvService {
  private final  PatientService patientService;

    public ByteArrayInputStream load() {
        List<Patient> patient = patientService.findAll();
        ByteArrayInputStream in = CSVHelper.tooCSV(patient);
        return in;
    }
}
