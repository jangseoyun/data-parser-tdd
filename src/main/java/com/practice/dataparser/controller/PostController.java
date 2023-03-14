package com.practice.dataparser.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/post")
public class PostController {

    @PostMapping("/domain")
    public String postExample() {
        return "hello post api";
    }

    @PostMapping("domain2")
    public String postMember(@RequestBody Map<String, Object> postData) {
        StringBuilder sb = new StringBuilder(); //builder pattern
        postData.entrySet().forEach(map -> sb.append(map.getKey() + ":" + map.getValue() + "\n"));
        return sb.toString();
    }
}
