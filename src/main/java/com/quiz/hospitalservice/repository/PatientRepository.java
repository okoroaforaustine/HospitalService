package com.quiz.hospitalservice.repository;

import com.quiz.hospitalservice.model.Patient;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PatientRepository extends CrudRepository<Patient,Long> {
    @Query(value = "select  * from Patient  where  age=2 ", nativeQuery = true)
    List<Patient> getListOfPatient2years();

    @Query(value = "select  * from Patient", nativeQuery = true)
            List<Patient> allPatient();

    @Modifying
    @Transactional
    @Query(value = "delete from Patient  where last_vist_date between :startDate and :endDate")
    void DeletePatientRecord( @Param("startDate")LocalDate startDate,@Param("endDate") LocalDate endDate );


}
