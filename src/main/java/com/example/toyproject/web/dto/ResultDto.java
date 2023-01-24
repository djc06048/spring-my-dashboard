package com.example.toyproject.web.dto;

import lombok.Getter;

@Getter
public class ResultDto<T> {
    private final T data;
    private final String message;
    private final String success;
    public ResultDto(T data, String message, String success){
        this.data=data;
        this.message=message;
        this.success=success;
    }
    public static <T> ResultDto<T> succeed(T data){
        return new ResultDto<>(data,null,"true");
    }
    public static <T> ResultDto<T> failed(Throwable throwable){
        return new ResultDto<>(null, throwable.getMessage(),"false");
    }
    public static <T> ResultDto<T> failed(String message){
        return new ResultDto<>(null,message,"false");
    }
}
