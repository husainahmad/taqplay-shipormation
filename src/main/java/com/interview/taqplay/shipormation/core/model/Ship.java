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
    @JsonProperty("shipType")
    private ShipType shipType;
    @JsonProperty("status")
    private Status status;
    @JsonProperty("speedOverGround")
    private Double speedOverGround;
    @JsonProperty("destination")
    private String destination;
    @JsonProperty("trueDestination")
    private String trueDestination;
    private ShipEvent event;
}
