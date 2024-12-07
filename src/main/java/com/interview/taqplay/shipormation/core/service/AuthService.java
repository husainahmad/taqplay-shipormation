package com.interview.taqplay.shipormation.core.service;

import com.interview.taqplay.shipormation.core.model.Authentication;

import java.io.IOException;

public interface AuthService {
    Authentication authenticate() throws IOException;
}
