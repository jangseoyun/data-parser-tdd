package com.practice.dataparser.controller;

import com.practice.dataparser.service.HospitalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.time.Instant;

@Slf4j
@RestController
@RequestMapping("/hospital")
@RequiredArgsConstructor
public class HospitalParserController {

    private final HospitalService hospitalService;

    /**
     *
     */
    @GetMapping()
    public void dataParser() {
        Instant start = Instant.now();
        String filename = "/Users/seoyun/codeLion/fulldata_01_01_02.tsv";

        hospitalService.insertLargeHospitalData(filename);

        Instant end = Instant.now();
        System.out.println("실행 소요 시간: " + Duration.between(start, end).toMillis());
    }


}
