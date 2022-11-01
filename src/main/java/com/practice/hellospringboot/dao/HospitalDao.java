package com.practice.hellospringboot.dao;

import com.practice.hellospringboot.domain.Hospital;
import com.practice.hellospringboot.domain.query.CrudQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@Component
public class HospitalDao {

    private final JdbcTemplate jdbcTemplate;
    private final CrudQuery crudQuery;

    public int save(Hospital hospital) {
        int saveResult = jdbcTemplate.update(crudQuery.save()
                , hospital.getId(), hospital.getOpenServiceName(), hospital.getOpenLocalGovernmentCode(), hospital.getManagementNumber(), hospital.getLicenseDate()
                , hospital.getBusinessStatus(), hospital.getBusinessStatusCode(), hospital.getPhone(), hospital.getFullAddress(), hospital.getRoadNameAddress()
                , hospital.getHospitalName(), hospital.getBusinessTypeName(), hospital.getHealthcareProviderCnt(), hospital.getPatientRoomCnt(), hospital.getTotalNumberOfBeds()
                , hospital.getTotalAreaSize());
        return saveResult;
    }

    /*private int id;/
    private String openServiceName;/
    private int openLocalGovernmentCode;/
    private String managementNumber;/
    private LocalDateTime LicenseDate;/
    private int businessStatus;/
    private int businessStatusCode;/
    private String phone;/
    private String fullAddress;/
    private String roadNameAddress;/
    private String hospitalName;/
    private String businessTypeName;/
    private int healthcareProviderCount;/
    private int patientRoomCount;/
    private int totalNumberOfBeds;/
    private float totalAreaSize;*/
    public Hospital findById(int id) {
        RowMapper<Hospital> rowMapper = new RowMapper<>() {
            @Override
            public Hospital mapRow(ResultSet rs, int rowNum) throws SQLException {
                Hospital hospital = new Hospital(
                        rs.getInt("id"), rs.getString("open_service_name"), rs.getInt("open_local_government_code")
                        , rs.getString("management_number"), rs.getObject("license_date", LocalDateTime.class)
                        , rs.getInt("business_status"), rs.getInt("business_status_code"), rs.getString("phone")
                        , rs.getString("full_address"), rs.getString("road_name_address"), rs.getString("hospital_name")
                        , rs.getString("business_type_name"), rs.getInt("healthcare_provider_cnt"), rs.getInt("patient_room_cnt")
                        , rs.getInt("total_number_of_beds"), rs.getFloat("total_area_size"));
                return hospital;
            }
        };
        return jdbcTemplate.queryForObject(crudQuery.findById(), rowMapper, id);
    }

    public int deleteAll() {
        return jdbcTemplate.update(crudQuery.deleteAll());
    }

    public int getCountAll() {
        return jdbcTemplate.queryForObject(crudQuery.getCountAll(), Integer.class);
    }


}
