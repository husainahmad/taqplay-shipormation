package com.interview.taqplay.shipormation.http.controller.handler;

import com.interview.taqplay.shipormation.core.exception.UnAuthorizedRequestException;
import com.interview.taqplay.shipormation.http.controller.dto.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
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
public class UnAuthorizedRequestExceptionHandler {

    private final MessageSource messageSource;

    @Autowired
    public UnAuthorizedRequestExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(UnAuthorizedRequestException.class)
    public ResponseEntity<ApiResponse>
            badRequestExceptionHandler(UnAuthorizedRequestException e, Locale locale) {

        String messageName = e.getMessage();
        Object[] args = e.getArgs();

        String message = messageSource.getMessage(messageName, args, locale);

        log.warn("UnAuthorized: {}", message);

        ApiResponse restAPIResponse = ApiResponse.builder()
                .httpStatus(HttpStatus.UNAUTHORIZED.value())
                .data(null)
                .error(message)
                .build();

        return new ResponseEntity<>(restAPIResponse, HttpStatus.UNAUTHORIZED);
    }
}
