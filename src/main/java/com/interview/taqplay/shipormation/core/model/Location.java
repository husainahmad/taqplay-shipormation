package com.interview.taqplay.shipormation.core.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Location {
    @JsonProperty("latitude")
    private double latitude;
    @JsonProperty("longitude")
    private double longitude;
    @JsonProperty("type")
    private String type;
    @JsonProperty("coordinates")
    private double[] coordinates;
}
