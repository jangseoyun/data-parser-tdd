package com.practice.hellospringboot.domain;

import dto.HospitalDto;

public class HospitalFactory {

    public HospitalDto createHospitalDto(Hospital hospital) {
        return new HospitalDto(hospital.getId()
                , hospital.getFullAddress()
                , hospital.getRoadNameAddress()
                , hospital.getHospitalName()
                , hospital.getHealthcareProviderCnt()
                , hospital.getTotalNumberOfBeds()
                , hospital.getTotalAreaSize()
                , makeBusinessStatusCode(hospital.getBusinessStatusCode()));
    }

    private String makeBusinessStatusCode(int statusCode) {
        //2: 휴업 3: 폐업 13: 영업중
        if (statusCode == 2) {
            return "휴업";
        }

        if (statusCode == 3) {
            return "폐업";
        }

        return "영업중";
    }
}
