package com.practice.hellospringboot.controller;

import com.practice.hellospringboot.domain.MemberDto;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping("/api/spring/v1")
@RestController
public class GetController {

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello() {
        System.out.println("start");
        return "hello world";
    }

    @GetMapping("/get-name")
    public String getName() {
        System.out.println("getMapping");
        return "seoyun";
    }

    @PostMapping("/post-name")
    public String getPost() {
        System.out.println("PostMapping");
        return "post name";
    }

    @GetMapping("/path/{id}")
    public String getIdPath(@PathVariable String id) {
        System.out.println(id);
        System.out.println("pathVariable");
        return "pathVariable";
    }

    @GetMapping("/request1")
    public String getRequestParam(@RequestParam String name
            , @RequestParam String email
            , @RequestParam String organization) {
        return String.format(" %s %s %s", name, email, organization);
    }

    @GetMapping("/request2")
    public String getRequestParam2(@RequestParam Map<String, String> param, @RequestParam String email, @RequestParam String organization) {
        param.entrySet().forEach(map -> {
            System.out.printf("key:%s value:%s \n", map.getKey(), map.getValue());
        });
        return "request2 호출 완료 되었습니다";
    }

    @GetMapping("/request3")
    public String getRequestParam3(MemberDto memberDto) {//RestController를 쓰면 아무것도 안쓰면 자동으로 requestBody
        System.out.println(memberDto);
        return "request3 호출 완료 되었습니다";
    }
}
