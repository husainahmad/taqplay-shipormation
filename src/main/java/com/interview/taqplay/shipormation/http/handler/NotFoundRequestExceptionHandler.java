package com.interview.taqplay.shipormation.http.handler;

import com.interview.taqplay.shipormation.core.exception.NotFoundRequestException;
import com.interview.taqplay.shipormation.http.dto.ApiResponse;
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
public class NotFoundRequestExceptionHandler {

    private final MessageSource messageSource;

    @Autowired
    public NotFoundRequestExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(NotFoundRequestException.class)
    public ResponseEntity<ApiResponse>
            badRequestExceptionHandler(NotFoundRequestException e, Locale locale) {

        String messageName = e.getMessage();
        Object[] args = e.getArgs();

        String message = messageSource.getMessage(messageName, args, locale);

        log.warn("NotFoundRequest: {}", message);

        ApiResponse restAPIResponse = ApiResponse.builder()
                .httpStatus(HttpStatus.BAD_REQUEST.value())
                .data(null)
                .error(message)
                .build();

        return new ResponseEntity<>(restAPIResponse, HttpStatus.BAD_REQUEST);
    }
}
