package com.interview.taqplay.shipormation.core.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Ship {
    @JsonProperty("mmsi")
    private String mssi;
    @JsonProperty("location")
    private Location location;

    private long courseOverGround;
    private long timeLastUpdate;
    @JsonProperty("imoNumber")
    private String imoNumber;
    @JsonProperty("name")
    private String name;
    private ShipEvent event;

}
