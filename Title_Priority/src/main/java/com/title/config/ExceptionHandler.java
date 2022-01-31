package com.title.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


import org.springframework.http.HttpStatus;

@ControllerAdvice
public class ExceptionHandler {
 
    @org.springframework.web.bind.annotation.ExceptionHandler(ExceptionClass.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    Map<String,String> showCustomMessage(Exception e){
 
 
        Map<String,String> response = new HashMap<>();
        response.put("status","Invalid Data");
 
        return response;
    }
}
