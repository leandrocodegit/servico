package com.simod.Exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.jwt.BadJwtException;
import org.springframework.security.oauth2.jwt.JwtDecoderInitializationException;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.security.oauth2.jwt.JwtValidationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import spinjar.com.jayway.jsonpath.spi.mapper.MappingException;


@ControllerAdvice
public class ExceptionResponseHandler {



    @ExceptionHandler(ExceptionSocket.class)
    public ResponseEntity<Error> handleExceptionSocket(ExceptionSocket exception, WebRequest request) {
        return buildErrorResponse(exception, HttpStatus.UNAUTHORIZED, request, exception.getMessage());
    }

    @ExceptionHandler(JwtException.class)
    public ResponseEntity<Error> handleJwtException(JwtException exception, WebRequest request) {
        return buildErrorResponse(exception, HttpStatus.UNAUTHORIZED, request, exception.getMessage());
    }
    @ExceptionHandler(JwtDecoderInitializationException.class)
    public ResponseEntity<Error> handleJWTDecodeException(JwtDecoderInitializationException exception, WebRequest request) {
        return buildErrorResponse(exception, HttpStatus.UNAUTHORIZED, request, exception.getMessage());
    }

    @ExceptionHandler(ExceptionAuthorization.class)
    public ResponseEntity<Error> handleExceptionAuthorization(ExceptionAuthorization exception) {
        return buildErrorResponse(exception, HttpStatus.FORBIDDEN, null, exception.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Error> handleRuntimeException(RuntimeException exception, WebRequest request) {
        return buildErrorResponse(exception, HttpStatus.BAD_REQUEST, request, exception.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Error> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception, WebRequest request) {
        return buildErrorResponse(exception,  HttpStatus.BAD_REQUEST, request, exception.getMessage());
    }
    @ExceptionHandler(ExceptionResponse.class)
    public ResponseEntity<Error> handleExceptionResponse(ExceptionResponse exception, WebRequest request) {
        return buildErrorResponse(exception, HttpStatus.BAD_REQUEST, request, exception.getMessage());
    }

    @ExceptionHandler(JwtValidationException.class)
    public ResponseEntity<Error> handleJwtValidationException(JwtValidationException exception, WebRequest request) {
        return buildErrorResponse(exception, HttpStatus.UNAUTHORIZED, request, "Acesso negado");
    }

    @ExceptionHandler(BadJwtException.class)
    public ResponseEntity<Error> handleBadJwtException(BadJwtException exception, WebRequest request) {
        return buildErrorResponse(exception, HttpStatus.UNAUTHORIZED, request, "Acesso negado");
    }

    @ExceptionHandler(MappingException.class)
    public ResponseEntity<Error> handleMappingException(ExceptionResponse exception, WebRequest request) {
        return buildErrorResponse(exception, HttpStatus.BAD_REQUEST, request, exception.getMessage());
    }

    private ResponseEntity<Error> buildErrorResponse(Exception exception, HttpStatus status, WebRequest request, String message) {
        Error errorDto = new Error(status.value(), message);
        return ResponseEntity.status(status).body(errorDto);
    }

    @AllArgsConstructor
    @Getter
    @Setter
    public static class Error {
        private final int status;
        private final String message;
    }
}
