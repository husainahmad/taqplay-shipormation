package com.interview.taqplay.shipormation.controller;

import com.interview.taqplay.shipormation.controller.dto.ApiResponse;
import com.interview.taqplay.shipormation.core.service.ShipService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class ShipController {

    private final ShipService service;

    @GetMapping("/ship/{mssi}")
    public ResponseEntity<ApiResponse> getShip(@PathVariable String mssi) {
        ApiResponse apiResponse = ApiResponse.builder()
                .data(service.getShipLocation(mssi))
                .build();
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/ship")
    public ResponseEntity<ApiResponse> getAllShip()  {
        ApiResponse apiResponse = ApiResponse.builder()
                .data(service.getShipAllLocation())
                .build();
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/ship/port/{portId}")
    public ResponseEntity<ApiResponse> getAllShipByPort(@PathVariable String portId) {
        ApiResponse apiResponse = ApiResponse.builder()
                .data(service.getShipAllShipByPort(portId))
                .build();
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

}
