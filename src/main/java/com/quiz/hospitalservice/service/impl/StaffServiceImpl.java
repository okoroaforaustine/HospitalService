package com.quiz.hospitalservice.service.impl;
import com.quiz.hospitalservice.dto.request.StaffRequest;
import com.quiz.hospitalservice.dto.response.ResponseStaffData;
import com.quiz.hospitalservice.model.Staff;
import com.quiz.hospitalservice.repository.StaffRepository;
import com.quiz.hospitalservice.service.StaffService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class StaffServiceImpl  implements StaffService {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final StaffRepository staffRepository;
    ObjectMapper objectMapper=new ObjectMapper();
    @Override
    public ResponseStaffData add ( Staff request ) {
        Staff staff = staffRepository.save(request);
        log.info("staff {} ",staff);
        ResponseStaffData resp = new  ResponseStaffData();
        if (Objects.isNull(staff)) {
            resp.setResponseCode("99");
            resp.setResponseCode("Failed");
        } else {
            resp.setResponseCode("000");
            resp.setResponseMessage("Successful");
            resp.setResult(staff);
        }
        return resp;

    }

    @Override
    public Staff ConvertDtoToModel ( StaffRequest staffRequest ) {
       Staff staff=new Staff();
         staff.setName(staffRequest.getName());
         staff.setRegistration_date(staffRequest.getStaffRegDate()) ;
         staff.setUuid(staffRequest.getUuid());
         return staff;
    }

    @Override
    public Staff  getStaff ( Staff staff ) {
       Staff getStaff=staffRepository.findByUuid(staff.getUuid());
       return getStaff;

    }

    @Override
    public ResponseStaffData   Update ( Staff staff ) {
        Staff getStaff = staffRepository.findByUuid(staff.getUuid());
        Staff staffObj = new Staff();
        ResponseStaffData responseData = new  ResponseStaffData();
        if (getStaff != null) {
            getStaff.setName(staff.getName());
            getStaff.setRegistration_date(staff.getRegistration_date());
            staffObj = staffRepository.save(getStaff);
            responseData.setResponseCode("000");
            responseData.setResponseMessage("Staff has been updated");
            responseData.setResult(staffObj);
            return responseData;
        }else{
            responseData.setResponseCode("99X");
            responseData.setResponseMessage("invalid staff UUID");
            return responseData;
        }
    }
}
