package com.quiz.hospitalservice.dto.response;

import com.quiz.hospitalservice.model.Staff;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data

public class ResponseStaffData {
    private  String responseCode;
    private String responseMessage;
    List<Staff> data=new ArrayList<>();
    private Object result;
}
