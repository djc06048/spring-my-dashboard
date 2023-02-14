package com.example.toyproject.web;

import com.example.toyproject.web.dto.HelloResponseDto;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Hidden
public class HelloController {
    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }
    @GetMapping("/hello/dto")
    public HelloResponseDto helloDto(@RequestParam("name")String name, @RequestParam("amount") int amount){
        return new HelloResponseDto(name,amount);
    }
}


