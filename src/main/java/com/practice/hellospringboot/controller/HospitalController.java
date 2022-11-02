package com.practice.hellospringboot.controller;

import com.practice.hellospringboot.dao.HospitalDao;
import com.practice.hellospringboot.domain.Hospital;
import com.practice.hellospringboot.domain.HospitalFactory;
import dto.HospitalDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/hospital")
@RestController
@RequiredArgsConstructor
public class HospitalController {

    private final HospitalDao hospitalDao;

    @GetMapping("/{id}")
    public ResponseEntity<HospitalDto> getHospital(@PathVariable("id") int id) {
        log.info("hospital controller : findById 요청 id = {}", id);

        Hospital getOne = hospitalDao.findById(id);
        log.info("hospital getOne : {}", getOne);

        HospitalDto hospitalDto = HospitalFactory.createHospitalDto(getOne);
        log.info("hospitalDto : {}", hospitalDto);
        return ResponseEntity.status(HttpStatus.OK).body(hospitalDto);
    }
}
