package com.practice.hellospringboot.controller;

import com.practice.hellospringboot.domain.MemberDto;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/put")
@RestController
public class PutController {

    @PutMapping("/member1")
    public String putRequest(@RequestBody MemberDto memberDto) {
        return memberDto.toString();
    }
}
