package com.quiz.hospitalservice.dto.request;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@Data
public class PatientRequest {
    private long id;
    private String uuid;
    private String name;
    private String age;
    private LocalDate lastVisitedDate;

}
