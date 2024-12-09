package com.interview.taqplay.shipormation.core.repository;

import com.interview.taqplay.shipormation.config.TaqplayProperties;
import com.interview.taqplay.shipormation.core.model.dao.UserDto;
import com.interview.taqplay.shipormation.core.external.service.ApiService;
import com.interview.taqplay.shipormation.core.model.Authentication;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.io.IOException;

@Slf4j
@AllArgsConstructor
@Repository
public class AuthRepository {

    private final ApiService apiService;
    private final TaqplayProperties taqplayProperties;

    public Authentication authenticate() {
        try {
            UserDto userDto = new UserDto();
            userDto.setUsername(taqplayProperties.getAuth().getUsername());
            userDto.setPassword(taqplayProperties.getAuth().getPassword());
            return apiService.postDataAsObject(taqplayProperties.getAuth().getUrl(), null,
                    userDto, Authentication.class);
        } catch (IOException e) {
            log.error(e.getMessage());
            return null;
        }
    }

}
