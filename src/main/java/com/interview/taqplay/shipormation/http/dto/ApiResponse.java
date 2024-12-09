package com.interview.taqplay.shipormation.http.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse {
    @Builder.Default
    private int httpStatus = HttpStatus.CREATED.value();
    @Builder.Default
    private Object data = HttpStatus.CREATED;
    private Object error;
}
