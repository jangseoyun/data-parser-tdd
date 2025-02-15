package com.practice.dataparser.service;

import com.practice.dataparser.domain.parser.ReadLineContext;
import com.practice.dataparser.dao.HospitalDao;
import com.practice.dataparser.domain.Hospital;
import com.practice.dataparser.domain.HospitalFactory;
import dto.HospitalDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class HospitalService {

    private final HospitalDao hospitalDao;
    private final ReadLineContext<Hospital> hospitalReadLineContext;

    public HospitalDto selectHospitalOne(int id) {
        return new HospitalFactory().createHospitalDto(hospitalDao.findById(id));
    }

    public int selectCount() {
        return hospitalDao.getCountAll();
    }
    @Transactional
    public void insertLargeHospitalData(String filename) {
        try {//test 15초
            List<Hospital> hospitals = hospitalReadLineContext.readByLine(filename);
            hospitalDao.batchInsert(hospitals);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("레코드에 문제가 있습니다");
            throw new RuntimeException();
        }
    }

}
