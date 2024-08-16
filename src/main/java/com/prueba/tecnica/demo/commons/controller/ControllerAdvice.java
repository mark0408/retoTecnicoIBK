package com.prueba.tecnica.demo.commons.controller;

import com.prueba.tecnica.demo.commons.dto.ErrorDTO;
import com.prueba.tecnica.demo.commons.exception.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {

  @ExceptionHandler(value = ValidationException.class)
  public ResponseEntity<ErrorDTO> validationExceptionHandler(ValidationException ex){
    ErrorDTO error = ErrorDTO.builder().code(ex.getCode()).message(ex.getMessage()).build();
    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
  }

}
