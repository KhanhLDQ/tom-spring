package org.tommap.tomlearnspring.eazy_school.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.tommap.tomlearnspring.eazy_school.model.ApiResponse;

@RestControllerAdvice(basePackages = "org.tommap.tomlearnspring.eazy_school.rest")
@Slf4j
public class GlobalExceptionRestHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleException(Exception ex) {
        ApiResponse<Void> resp = ApiResponse.<Void>builder()
                .code(500)
                .message(ex.getMessage())
                .build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resp);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request
    ) {
        ApiResponse<Void> resp = ApiResponse.<Void>builder()
                .code(400)
                .message(ex.getBindingResult().toString())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
    }
}
