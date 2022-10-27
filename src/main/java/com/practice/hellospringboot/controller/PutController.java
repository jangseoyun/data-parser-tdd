package com.practice.hellospringboot.controller;

import com.practice.hellospringboot.domain.MemberDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/put")
@RestController
public class PutController {

    //String으로 반환
    @PutMapping("/member1")
    public String putRequest(@RequestBody MemberDto memberDto) {
        return memberDto.toString();
    }

    //객체로 반환
    @PutMapping("/member2")
    public MemberDto putRequest2(@RequestBody MemberDto memberDto) {
        return memberDto;
    }

    @PutMapping("/member3")
    public ResponseEntity<MemberDto> putRequest3(@RequestBody MemberDto memberDto) {
        return ResponseEntity
                    .status(HttpStatus.ACCEPTED)
                    .body(memberDto);
    }
}
