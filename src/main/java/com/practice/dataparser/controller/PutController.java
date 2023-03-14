package com.practice.dataparser.controller;

import com.practice.dataparser.domain.MemberDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
                .status(HttpStatus.OK)//상태 값 지정 가능
                .body(memberDto);
    }

    /*@DeleteMapping(value = "/delete/{id}")
    public String getRequest4(@PathVariable String id) {

    }*/
}
