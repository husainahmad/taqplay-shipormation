package com.interview.taqplay.shipormation.controller.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@Builder

public class ApiResponse {
    @Builder.Default
    private int httpStatus = HttpStatus.CREATED.value();
    @Builder.Default
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object data = HttpStatus.CREATED;
}
