package com.practice.hellospringboot.dao;

import com.practice.hellospringboot.domain.Hospital;
import com.practice.hellospringboot.domain.query.CrudQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class HospitalDao {

    private final JdbcTemplate jdbcTemplate;
    private final CrudQuery crudQuery;

    public int save(Hospital hospital) {
        int saveResult = jdbcTemplate.update(crudQuery.save()
                , hospital.getId(), hospital.getOpenServiceName(), hospital.getOpenLocalGovernmentCode(), hospital.getManagementNumber(), hospital.getLicenseDate()
                , hospital.getBusinessStatus(), hospital.getBusinessStatusCode(), hospital.getPhone(), hospital.getFullAddress(), hospital.getRoadNameAddress()
                , hospital.getHospitalName(), hospital.getBusinessTypeName(), hospital.getHealthcareProviderCount(), hospital.getPatientRoomCount(), hospital.getTotalNumberOfBeds()
                , hospital.getTotalAreaSize());
        return saveResult;
    }
}
