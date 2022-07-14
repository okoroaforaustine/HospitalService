package com.quiz.hospitalservice.dto.response;

import com.quiz.hospitalservice.model.Patient;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ResponsePatientData {
    private  String responseCode;
    private String responseMessage;
    List<Patient> data=new ArrayList<>();
    private Object result;
}
