package com.interview.taqplay.shipormation.config;

import lombok.Data;

import java.io.Serializable;

@Data
public class AuthProperties implements Serializable {
    private String url;
    private String username;
    private String password;
}
