package com.practice.hellospringboot.controller;

import org.springframework.web.bind.annotation.*;

@RequestMapping("/api.github.com")
@RestController
public class GitController {

    //https://api.github.com/repos/Kyeongrok/java-algorithm/commits?since=2022-10-27T10:50:50Z
    @GetMapping("/repos/{owner}/{repo}/commits")
    public String getUserGithub(@PathVariable(value = "owner") String owner
            , @PathVariable(value = "repo")String repo
            , @RequestParam String since) {
        return "깃헙 호츌";
    }
}
