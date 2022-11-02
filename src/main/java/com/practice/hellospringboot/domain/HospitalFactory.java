package com.practice.hellospringboot.domain;

import dto.HospitalDto;

public class HospitalFactory {

    public static HospitalDto createHospitalDto(Hospital hospital) {

        return new HospitalDto(hospital.getId()
                , hospital.getFullAddress()
                , hospital.getRoadNameAddress()
                , hospital.getHospitalName()
                , hospital.getHealthcareProviderCnt()
                , hospital.getTotalNumberOfBeds()
                , hospital.getTotalAreaSize());
    }
}
