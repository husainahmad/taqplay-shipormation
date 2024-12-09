package com.interview.taqplay.shipormation.core.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.interview.taqplay.shipormation.config.TaqplayProperties;
import com.interview.taqplay.shipormation.core.external.service.ApiService;
import com.interview.taqplay.shipormation.core.model.Authentication;
import com.interview.taqplay.shipormation.core.model.Ship;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;

@Slf4j
@AllArgsConstructor
@Repository
public class ShipRepository {

    private final ApiService apiService;
    private final TaqplayProperties taqplayProperties;
    private final ObjectMapper objectMapper;

    public List<Ship> getList(Authentication authentication, String mssi) {
        try {
            return apiService.getDataAsList(taqplayProperties.getShip().getUrl()
                            .concat("/").concat(mssi), authentication,
                    new TypeReference<>() {
                    });
        } catch (IOException e) {
            log.error(e.getMessage());
        }

        return List.of();
    }

    public List<Ship> getAllList(Authentication authentication){
        try {
            return apiService.getDataAsList(taqplayProperties.getShip().getUrl(), authentication,
                    new TypeReference<>() {
                    });
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return List.of();
    }

}
