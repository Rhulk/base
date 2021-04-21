package com.alquiler.reservas.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class DemoExceptionHandler {

	//up
    @ExceptionHandler(Exception.class)
    public String exceptionHandler(){
        return "error";
    }
}
