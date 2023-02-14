package com.example.toyproject.web.dto;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Hidden
public class HelloResponseDto {
    private final String name;
    private final int amount;
}
