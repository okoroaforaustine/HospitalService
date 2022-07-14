package com.quiz.hospitalservice.repository;

import com.quiz.hospitalservice.model.Staff;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffRepository extends CrudRepository<Staff,Long> {

    Staff findByUuid( String uuid);
}
