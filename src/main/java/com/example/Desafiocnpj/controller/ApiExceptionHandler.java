package com.example.Desafiocnpj.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.Desafiocnpj.exception.ApiIntegrationException;
import com.example.Desafiocnpj.model.dto.ErroResponse;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroResponse> handleValidationException(MethodArgumentNotValidException exception) {
        String message = exception.getBindingResult().getFieldErrors().stream()
                .findFirst()
                .map(error -> error.getDefaultMessage())
                .orElse("Dados inválidos.");

        return ResponseEntity.badRequest().body(new ErroResponse(message));
    }

    @ExceptionHandler(ApiIntegrationException.class)
    public ResponseEntity<ErroResponse> handleApiIntegrationException(ApiIntegrationException exception) {
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
                .body(new ErroResponse("Não foi possível consultar os serviços externos no momento."));
    }
}