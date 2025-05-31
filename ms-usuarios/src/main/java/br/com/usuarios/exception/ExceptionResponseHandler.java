package br.com.usuarios.exception;


import br.modelos.Exception.ExceptionAuthorization;
import br.modelos.Exception.ExceptionResponse;
import br.modelos.Exception.FeignClientException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mapping.MappingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;


@ControllerAdvice
public class ExceptionResponseHandler {



    @ExceptionHandler(ExceptionAuthorization.class)
    public ResponseEntity<Error> handleExceptionAuthorization(ExceptionAuthorization exception) {
        return buildErrorResponse(exception, HttpStatus.UNAUTHORIZED, null, exception.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Error> handleRuntimeException(RuntimeException exception, WebRequest request) {
        return buildErrorResponse(exception, HttpStatus.BAD_REQUEST, request, exception.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Error> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception, WebRequest request) {
        return buildErrorResponse(exception, HttpStatus.BAD_REQUEST, request, exception.getMessage());
    }
    @ExceptionHandler(ExceptionResponse.class)
    public ResponseEntity<Error> handleExceptionResponse(ExceptionResponse exception, WebRequest request) {
        return buildErrorResponse(exception, HttpStatus.BAD_REQUEST, request, exception.getMessage());
    }

    @ExceptionHandler(MappingException.class)
    public ResponseEntity<Error> handleMappingException(ExceptionResponse exception, WebRequest request) {
        return buildErrorResponse(exception, HttpStatus.BAD_REQUEST, request, exception.getMessage());
    }

    @ExceptionHandler(FeignClientException.class)
    public ResponseEntity<Error> handleFeignClientException(FeignClientException exception, WebRequest request) {
        return buildErrorResponse(exception, HttpStatus.valueOf(exception.getStatus()), request, exception.getMessage());
    }

    private ResponseEntity<Error> buildErrorResponse(Exception exception, HttpStatus status, WebRequest request, String message) {
            exception.printStackTrace();        
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
