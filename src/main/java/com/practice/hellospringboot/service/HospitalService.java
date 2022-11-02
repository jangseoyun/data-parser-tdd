package com.practice.hellospringboot.service;

import com.practice.hellospringboot.dao.HospitalDao;
import com.practice.hellospringboot.domain.HospitalFactory;
import dto.HospitalDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class HospitalService {

    private final HospitalDao hospitalDao;

    public HospitalDto selectHospitalOne(int id) {
        return new HospitalFactory().createHospitalDto(hospitalDao.findById(id));
    }

    public int selectCount() {
        return hospitalDao.getCountAll();
    }

}
