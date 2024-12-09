package com.interview.taqplay.shipormation.core.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UnAuthorizedRequestException extends RuntimeException {
    private final String message;
    private final transient Object[] args;
}
