package com.quiz.hospitalservice.controller;

import com.quiz.hospitalservice.dto.request.StaffRequest;
import com.quiz.hospitalservice.dto.response.ResponseStaffData;
import com.quiz.hospitalservice.model.Staff;
import com.quiz.hospitalservice.service.StaffService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/staff")
@RequiredArgsConstructor
public class StaffController {
    Logger logger = LoggerFactory.getLogger(StaffController.class);
     private final StaffService staffService;
    @PostMapping(value = "/add")
    public ResponseEntity<?> AddStaff( @RequestBody StaffRequest request){
        ResponseStaffData response=new  ResponseStaffData ();
        logger.info("creating Patient Record {} ",request);
        Staff staff=   staffService.ConvertDtoToModel(request);
         response = staffService.add(staff);
        return  ResponseEntity.ok().body(response);

    }
    @PostMapping(value = "/update")
    public ResponseEntity<?> UpdateStaff( @RequestBody StaffRequest request){
        ResponseStaffData response=new  ResponseStaffData ();
        Staff staff=   staffService.ConvertDtoToModel(request);
        Staff staffModel=   staffService.ConvertDtoToModel(request);
        response = staffService.Update(staffModel);
        return  ResponseEntity.ok().body(response);

    }
}
