package com.practice.dataparser.domain.query;

import org.springframework.stereotype.Component;

@Component
public class HospitalCrudQuery implements CrudQuery {

    @Override
    public String save() {
        String query = "";
        query = "insert into nation_wide_hospitals (id, open_service_name, open_local_government_code, management_number, license_date,"
                + " business_status, business_status_code, phone, full_address, road_name_address,"
                + " hospital_name, business_type_name, healthcare_provider_cnt, patient_room_cnt,"
                + " total_number_of_beds, total_area_size)"
                + " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        return query;
    }

    @Override
    public String findById() {
        String query = "";
        query = "select * "
                + " from nation_wide_hospitals "
                + " where id = ?;";
        return query;
    }

    @Override
    public String deleteAll() {
        String query = "";
        query = "delete from nation_wide_hospitals;";
        return query;
    }

    @Override
    public String getCountAll() {
        String query = "";
        query = "select count(*)"
                + " from nation_wide_hospitals;";
        return query;
    }

    @Override
    public String findAll() {
        String query = "";
        query = "select * from nation_wide_hospitals limit 500;";
        return query;
    }

}
