package com.interview.taqplay.shipormation.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.io.Serializable;

@Configuration
@Data
@ConfigurationProperties("taqplay")
public class TaqplayProperties implements Serializable {
    private transient AuthProperties auth;
    private transient ShipProperties ship;
}
