package com.practice.hellospringboot.controller;

import com.practice.hellospringboot.domain.MemberDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RequestMapping("/api/v1/get")
@RestController
public class GetController {

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello() {
        log.info("request get mapping 요청");
        return "hello world";
    }

    @GetMapping("/get-name")
    public String getName() {
        log.info("getMapping 요청");
        return "seoyun";
    }

    @PostMapping("/post-name")
    public String getPost() {
        System.out.println("PostMapping");
        return "post name";
    }

    @GetMapping("/path/{id}")
    public String getIdPath(@PathVariable String id) {
        log.info("getMapping variable:{}", id);
        return "pathVariable";
    }

    @GetMapping("/request1")
    public String getRequestParam(@RequestParam String name
            , @RequestParam String email
            , @RequestParam String organization) {
        log.info("getMapping parameter ? " + name);// +로 할 경우 문자열 연산자를 먼저 실행한 뒤 log를 남기기 떄문에 리소스 낭비
        return String.format(" %s %s %s", name, email, organization);
    }

    @GetMapping("/request2")
    public String getRequestParam2(@RequestParam Map<String, String> param) {
        log.info("getMapping param Map<String, String> {}", param);
        param.entrySet().forEach(map -> {
            System.out.printf("key:%s value:%s \n", map.getKey(), map.getValue());
        });
        return "request2 호출 완료 되었습니다";
    }

    @GetMapping("/request3")
    public String getRequestParam3(MemberDto memberDto) {//RestController를 쓰면 아무것도 안쓰면 자동으로 requestBody
        log.info("getMapping Object 바인딩 MemberDto {}", memberDto);
        return "request3 호출 완료 되었습니다";
    }
}
