package com.quiz.hospitalservice.service;

import com.quiz.hospitalservice.dto.response.ResponseStaffData;
import com.quiz.hospitalservice.dto.request.StaffRequest;
import com.quiz.hospitalservice.model.Staff;
import org.springframework.stereotype.Service;


public interface StaffService {
    ResponseStaffData add( Staff request );
    Staff ConvertDtoToModel( StaffRequest patientRequest);
    Staff getStaff(Staff staff);
    ResponseStaffData Update( Staff staff);
}
