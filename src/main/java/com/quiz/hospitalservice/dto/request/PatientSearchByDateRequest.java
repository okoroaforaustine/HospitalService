package com.quiz.hospitalservice.dto.request;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class PatientSearchByDateRequest {
    private  String staffId;
    private LocalDate startDate;
    private LocalDate endDate;
}
