package com.interview.taqplay.shipormation.core.model;

import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Getter
@Builder
public class Authentication {
    private String userName;
    private String refreshToken;
    private String token;
    private long expiredInSeconds;
    private Date createdAt;
}
