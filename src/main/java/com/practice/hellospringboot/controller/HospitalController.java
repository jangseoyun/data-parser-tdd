package com.practice.hellospringboot.controller;

import com.practice.hellospringboot.service.HospitalService;
import dto.HospitalDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/hospital")
@RestController
@RequiredArgsConstructor
public class HospitalController {

    private final HospitalService hospitalService;

    @GetMapping("/{id}")
    public ResponseEntity<HospitalDto> getHospital(@PathVariable("id") int id) {
        log.info("hospital controller : findById 요청 id = {}", id);

        HospitalDto hospitalOne = hospitalService.selectHospitalOne(id);
        log.info("hospitalDto hospitalOne : {}", hospitalOne);

        return ResponseEntity.status(HttpStatus.OK).body(hospitalOne);
    }

    @GetMapping("/count")
    public ResponseEntity getCount() {
        log.info("hospital controller : get-count 요청");
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(hospitalService.selectCount());
    }
}
