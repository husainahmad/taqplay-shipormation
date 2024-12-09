package com.interview.taqplay.shipormation.http.handler;

import com.interview.taqplay.shipormation.http.dto.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Locale;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
@Slf4j
public class InternalErrorExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse>
            internalErrorExceptionHandler(Exception e, Locale locale) {

        ApiResponse restAPIResponse = ApiResponse.builder()
                .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .data(null)
                .error(HttpStatus.INTERNAL_SERVER_ERROR)
                .build();

        return new ResponseEntity<>(restAPIResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
