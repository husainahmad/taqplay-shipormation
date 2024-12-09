package com.interview.taqplay.shipormation.core.service;

import com.interview.taqplay.shipormation.core.model.Ship;

import java.util.List;

public interface ShipService {
    List<Ship> getShipLocation(String mssi);
    List<Ship> getShipAllLocation();
    List<Ship> getShipAllShipByPort(String port);
}
