package com.interview.taqplay.shipormation.core.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.interview.taqplay.shipormation.config.TaqplayProperties;
import com.interview.taqplay.shipormation.core.exception.NotFoundRequestException;
import com.interview.taqplay.shipormation.core.model.Authentication;
import com.interview.taqplay.shipormation.core.model.Ship;
import com.interview.taqplay.shipormation.core.model.ShipEvent;
import com.interview.taqplay.shipormation.core.repository.ShipRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@AllArgsConstructor
@Service("shipService")
public class ShipServiceImpl implements ShipService {

    private final AuthService authService;
    private final ShipRepository shipRepository;
    private final ObjectMapper objectMapper;
    private final TaqplayProperties taqplayProperties;

    @Override
    public List<Ship> getShipLocation(String mssi) {
        List<Ship> ships = new ArrayList<>();
        shipRepository.getList(authenticate(), mssi).forEach(ship -> {
            ship.setEvent(ShipEvent.LEAVE);
            if (ship.getLocation()!=null &&
                    isLocationInPolygon(ship.getLocation().getLatitude(), ship.getLocation().getLongitude(),
                        getRoterdamLocation())) {
                    ship.setEvent(ShipEvent.ENTER);
            }
            ships.add(ship);
        });
        return ships;
    }

    @Override
    public List<Ship> getShipAllLocation() {
        return shipRepository.getAllList(authenticate());
    }

    @Override
    public List<Ship> getShipAllShipByPort(String port) {

        List<Ship> ships = getShipAllLocation();
        List<Ship> shipInRosterdam = new ArrayList<>();

        ships.forEach(ship -> {
            try {
                if ((ship.getLocation()!=null)
                        && isLocationInPolygon(ship.getLocation().getLatitude(), ship.getLocation().getLongitude(),
                        getRoterdamLocation())) {
                    shipInRosterdam.add(ship);
                }
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        });

        return shipInRosterdam;
    }

    private Authentication authenticate() {
        try {
            return authService.authenticate();
        } catch (Exception e) {
            throw  new NotFoundRequestException("Not able to authenticate", null);
        }
    }

    private boolean isLocationInPolygon(double lat, double lon, double[][] polygonPoints) {
        Polygon polygon = new Polygon();
        for (double[] point: polygonPoints) {
            polygon.addPoint((int) (point[0] * 1_000_000), (int) (point[1] * 1_000_000));
        }

        int scaledX = (int) (lat * 1_000_000);
        int scaledY = (int) (lon * 1_000_000);

        return polygon.contains(scaledX, scaledY);
    }

    private double[][] getRoterdamLocation() {
        try {
            return objectMapper.readValue(
                    taqplayProperties.getShip().getRoterdam(), new TypeReference<>() {
                    });
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
        }
        return new double[0][0];
    }
}
