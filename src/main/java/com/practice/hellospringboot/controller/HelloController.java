package com.practice.hellospringboot.controller;

import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/spring/v1")
@RestController
public class HelloController {

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
    public String getRequestParam(@RequestParam String name, @RequestParam String email, @RequestParam String organization) {
        return String.format(" %s %s %s", name, email, organization);
    }

}
