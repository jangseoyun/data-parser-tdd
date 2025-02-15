package com.practice.dataparser.dao;

import com.practice.dataparser.domain.Hospital;
import com.practice.dataparser.domain.query.CrudQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

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

    public int[] batchInsert(List<Hospital> hospitalList) {
        return jdbcTemplate.batchUpdate(crudQuery.save(), new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                Hospital hospital = hospitalList.get(i);
                ps.setLong(1, hospital.getId());
                ps.setString(2, hospital.getOpenServiceName());
                ps.setInt(3, hospital.getOpenLocalGovernmentCode());
                ps.setString(4, hospital.getManagementNumber());
                ps.setObject(5, hospital.getLicenseDate());
                ps.setInt(6, hospital.getBusinessStatus());
                ps.setInt(7, hospital.getBusinessStatusCode());
                ps.setString(8, hospital.getPhone());
                ps.setString(9, hospital.getFullAddress());
                ps.setString(10, hospital.getRoadNameAddress());
                ps.setString(11, hospital.getHospitalName());
                ps.setString(12, hospital.getBusinessTypeName());
                ps.setInt(13, hospital.getHealthcareProviderCnt());
                ps.setInt(14, hospital.getPatientRoomCnt());
                ps.setInt(15, hospital.getTotalNumberOfBeds());
                ps.setFloat(16, hospital.getTotalAreaSize());
            }

            @Override
            public int getBatchSize() {
                return hospitalList.size();
            }
        });
    }

    public Hospital findById(int id) {
        return jdbcTemplate.queryForObject(crudQuery.findById(), getHospitalRowMapper(), id);
    }

    public int deleteAll() {
        return jdbcTemplate.update(crudQuery.deleteAll());
    }

    public int getCountAll() {
        return jdbcTemplate.queryForObject(crudQuery.getCountAll(), Integer.class);
    }

    public List<Hospital> findAll() {
        return jdbcTemplate.query(crudQuery.findAll(), getHospitalRowMapper());
    }

    private RowMapper<Hospital> getHospitalRowMapper() {
        RowMapper<Hospital> rowMapper = new RowMapper<>() {
            @Override
            public Hospital mapRow(ResultSet rs, int rowNum) throws SQLException {
                Hospital hospital = new Hospital(
                        rs.getLong("id"), rs.getString("open_service_name"), rs.getInt("open_local_government_code")
                        , rs.getString("management_number"), rs.getObject("license_date", LocalDateTime.class)
                        , rs.getInt("business_status"), rs.getInt("business_status_code"), rs.getString("phone")
                        , rs.getString("full_address"), rs.getString("road_name_address"), rs.getString("hospital_name")
                        , rs.getString("business_type_name"), rs.getInt("healthcare_provider_cnt"), rs.getInt("patient_room_cnt")
                        , rs.getInt("total_number_of_beds"), rs.getFloat("total_area_size"));
                return hospital;
            }
        };
        return rowMapper;
    }

}
