package com.interview.taqplay.shipormation.core.service;

import com.interview.taqplay.shipormation.core.exception.UnAuthorizedRequestException;
import com.interview.taqplay.shipormation.core.model.Authentication;
import com.interview.taqplay.shipormation.core.repository.AuthRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@AllArgsConstructor
@Service("authService")
public class AuthServiceImpl implements AuthService {

    private final AuthRepository authRepository;

    @Override
    public Authentication authenticate() throws IOException {
        Authentication authentication = authRepository.authenticate();
        if (authentication==null) {
            throw new UnAuthorizedRequestException("Authorization Failed ", null);
        }
        return authentication;
    }
}
