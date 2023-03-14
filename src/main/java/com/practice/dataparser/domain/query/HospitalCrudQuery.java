package com.practice.dataparser.domain.query;

import org.springframework.stereotype.Component;

@Component
public class HospitalCrudQuery implements CrudQuery {

    @Override
    public String save() {
        String query = "";
        query = "insert into t_hospital_data (id, open_service_name, open_local_government_code, management_number, license_date,"
                + " business_status, business_status_code, phone, full_address, road_name_address,"
                + " hospital_name, business_type_name, healthcare_provider_cnt, patient_room_cnt,"
                + " total_number_of_beds, total_area_size)"
                + " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        return query;
    }

    @Override
    public String findById() {
        String query = "";
        query = "select *"
                + " from t_hospital_data"
                + " where id = ?;";
        return query;
    }

    @Override
    public String deleteAll() {
        String query = "";
        query = "delete from t_hospital_data;";
        return query;
    }

    @Override
    public String getCountAll() {
        String query = "";
        query = "select count(*)"
                + " from t_hospital_data;";
        return query;
    }

    @Override
    public String findAll() {
        String query = "";
        query = "select * from t_hospital_data limit 500;";
        return query;
    }

}
