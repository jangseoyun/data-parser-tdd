package com.practice.hellospringboot.domain.parser;

import com.practice.hellospringboot.dao.HospitalDao;
import com.practice.hellospringboot.domain.Hospital;
import com.practice.hellospringboot.service.HospitalService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@DisplayName("HospitalParserTest")
class HospitalParserTest {

    String line1 = "\"1\",\"의원\",\"01_01_02_P\",\"3620000\",\"PHMA119993620020041100004\",\"19990612\",\"\",\"01\",\"영업/정상\",\"13\",\"영업중\",\"\",\"\",\"\",\"\",\"062-515-2875\",\"\",\"500881\",\"광주광역시 북구 풍향동 565번지 4호 3층\",\"광주광역시 북구 동문대로 24, 3층 (풍향동)\",\"61205\",\"효치과의원\",\"20211115113642\",\"U\",\"2021-11-17 02:40:00.0\",\"치과의원\",\"192630.735112\",\"185314.617632\",\"치과의원\",\"1\",\"0\",\"0\",\"52.29\",\"401\",\"치과\",\"\",\"\",\"\",\"0\",\"0\",\"\",\"\",\"0\",\"\",";

    @Autowired
    ReadLineContext<Hospital> hospitalReadLineContext;
    @Autowired
    HospitalService hospitalService;
    @Autowired
    HospitalDao hospitalDao;
    HospitalParser hp;

    @BeforeEach
    void setUp() {
        hp = new HospitalParser();
        //hospitalDao.deleteAll();
    }

    @DisplayName("csv 한 줄을 hospital로 잘 만드는지 확인")
    @Test
    void convertToHospital() {
        Hospital hospital = hp.parse(line1);

        assertEquals(1, hospital.getId()); // col:0
        assertEquals("의원", hospital.getOpenServiceName());//col:1
        assertEquals(3620000, hospital.getOpenLocalGovernmentCode()); // col: 3
        assertEquals("PHMA119993620020041100004", hospital.getManagementNumber()); // col:4
        assertEquals(LocalDateTime.of(1999, 6, 12, 0, 0, 0), hospital.getLicenseDate()); //19990612 //col:5
        assertEquals(1, hospital.getBusinessStatus()); //col:7
        assertEquals(13, hospital.getBusinessStatusCode());//col:9
        assertEquals("062-515-2875", hospital.getPhone());//col:
        assertEquals("광주광역시 북구 풍향동 565번지 4호 3층", hospital.getFullAddress()); //col:18
        assertEquals("광주광역시 북구 동문대로 24, 3층 (풍향동)", hospital.getRoadNameAddress());//col:19
        assertEquals("효치과의원", hospital.getHospitalName());//col:21
        assertEquals("치과의원", hospital.getBusinessTypeName());//col:25
        assertEquals(1, hospital.getHealthcareProviderCnt()); //col:30
        assertEquals(0, hospital.getPatientRoomCnt()); //col:31
        assertEquals(0, hospital.getTotalNumberOfBeds()); //col:32
        assertEquals(52.29f, hospital.getTotalAreaSize()); //col:33

    }

    @DisplayName("10만건 이상 데이터 파싱 확인")
    @Test
    void parserCheck() throws IOException {
        String filename = "/Users/seoyun/codeLion/fulldata_01_01_02.tsv";
        List<Hospital> hospitals = hospitalReadLineContext.readByLine(filename);
        System.out.println(hospitals.size() + "건의 데이터 저장");
        assertTrue(hospitals.size() > 1000);
        assertTrue(hospitals.size() > 10000);

        for (int i = 0; i < 10; i++) {
            System.out.println(hospitals.get(i).getHospitalName());
        }
    }

    @DisplayName("batch를 사용하여 111918건의 데이터 insert")
    @Test
    void batchInsert() throws IOException {
        Instant start = Instant.now();
        String filename = "/Users/seoyun/codeLion/fulldata_01_01_02.tsv";

        hospitalService.insertLargeHospitalData(filename);

        assertEquals(111918, hospitalDao.getCountAll());
        Instant end = Instant.now();
        System.out.println("실행 소요 시간: " + Duration.between(start, end).toMillis());
    }

    @DisplayName("batch를 사용하지 않고 111918건의 데이터 insert")
    @Test
    void insertAll() throws IOException {
        String filename = "/Users/seoyun/codeLion/fulldata_01_01_02.tsv";
        List<Hospital> hospitals = hospitalReadLineContext.readByLine(filename);

        for (Hospital hospital : hospitals) {
            hospitalDao.save(hospital);
        }

        assertEquals(111918, hospitalDao.getCountAll());
    }

    @DisplayName("save and getHospitalOne")
    @Test
    void saveAndGetOne() {
        Hospital hospital = hp.parse(line1);

        assertEquals(1, hospitalDao.save(hospital));
        Hospital findOne = hospitalDao.findById(1);

        assertEquals(1, findOne.getId());
        assertEquals("의원", findOne.getOpenServiceName());
        assertEquals(3620000, findOne.getOpenLocalGovernmentCode());
        assertEquals("PHMA119993620020041100004", findOne.getManagementNumber());
        assertEquals(LocalDateTime.of(1999, 6, 12, 0, 0, 0), hospital.getLicenseDate()); //19990612 //col:5
        assertTrue(findOne.getLicenseDate().isEqual(findOne.getLicenseDate()));
        assertEquals(1, findOne.getBusinessStatus());
        assertEquals(13, findOne.getBusinessStatusCode());
        assertEquals("062-515-2875", findOne.getPhone());
        assertEquals("광주광역시 북구 풍향동 565번지 4호 3층", findOne.getFullAddress());
        assertEquals("광주광역시 북구 동문대로 24, 3층 (풍향동)", findOne.getRoadNameAddress());
        assertEquals("효치과의원", findOne.getHospitalName());
        assertEquals("치과의원", findOne.getBusinessTypeName());
        assertEquals(1, findOne.getHealthcareProviderCnt());
        assertEquals(0, findOne.getPatientRoomCnt());
        assertEquals(0, findOne.getTotalNumberOfBeds());
        assertEquals(52.29f, findOne.getTotalAreaSize());
    }

    /*@DisplayName("테이블 데이터 전체 삭제 확인")
    @Test
    void deleteAllCheck() {
        Hospital hospital = hp.parse(line1);
        hospitalDao.save(hospital);
        assertEquals(1, hospitalDao.deleteAll());
    }*/

    @DisplayName("테이블 전체 카운트 가져오기")
    @Test
    void getCountAll() {
        Hospital hospital = hp.parse(line1);
        assertEquals(0, hospitalDao.getCountAll());

        hospitalDao.save(hospital);
        assertEquals(1, hospitalDao.getCountAll());
    }
}