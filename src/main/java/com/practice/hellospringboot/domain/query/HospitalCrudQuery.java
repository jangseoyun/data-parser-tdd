package com.practice.hellospringboot.domain.query;

import org.springframework.stereotype.Component;

@Component
public class HospitalCrudQuery implements CrudQuery {

    @Override
    public String save() {
        String query = "";
        query = "insert into nation_wide_hospitals (id, open_service_name, open_local_government_code, management_number, license_date,\\n\" +\n" +
                "                \"                                   business_status, business_status_code, phone, full_address, road_name_address,\\n\" +\n" +
                "                \"                                   hospital_name, business_type_name, healthcare_provider_cnt, patient_room_cnt,\\n\" +\n" +
                "                \"                                   total_number_of_beds, total_area_size)\\n\" +\n" +
                "                \"values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

        return query;
    }
}
