package com.quiz.hospitalservice.dto.request;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDate;

@Data
public class StaffRequest {
    private Long id;
    private String uuid;
    private String name;
    private LocalDate staffRegDate;


}
