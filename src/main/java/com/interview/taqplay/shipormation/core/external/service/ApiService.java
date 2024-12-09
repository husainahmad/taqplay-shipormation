package com.interview.taqplay.shipormation.core.external.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.interview.taqplay.shipormation.core.model.Authentication;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@AllArgsConstructor
@Service
public class ApiService {

    private final CloseableHttpClient httpClient;
    private final ObjectMapper objectMapper;

    public <T> T getDataAsList(String url, Authentication authentication, TypeReference<T> typeReference) throws IOException {
        objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        return objectMapper.readValue(getDataAsString(url, authentication), typeReference);
    }

    public String getDataAsString(String url, Authentication authentication) throws ParseException, IOException {
        HttpGet httpRequest = new HttpGet(url);
        httpRequest.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");

        if (authentication != null) {
            httpRequest.setHeader(HttpHeaders.AUTHORIZATION, authentication.getToken());
        }

        HttpResponse httpResponse = httpClient.execute(httpRequest);

        String responseAsString = EntityUtils.toString(httpResponse.getEntity());
        getDebug(responseAsString);
        return responseAsString;
    }

    private static void getDebug(String responseAsString) {
        log.debug("Response {}", responseAsString);
    }

    public <T> T postDataAsObject(String url, Authentication authentication,
                          Object payload, Class<T> responseType) throws IOException {
        return objectMapper.readValue(postDataAsString(url, authentication, payload), responseType);
    }

    public String postDataAsString(String url, Authentication authentication,
                                   Object payload) throws ParseException, IOException {
        HttpPost httpRequest = new HttpPost(url);
        String jsonPayload = objectMapper.writeValueAsString(payload);
        httpRequest.setEntity(new StringEntity(jsonPayload));
        httpRequest.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");

        if (authentication != null) {
            httpRequest.setHeader(HttpHeaders.AUTHORIZATION, authentication.getToken());
        }

        HttpResponse httpResponse = httpClient.execute(httpRequest);
        String responseAsString = EntityUtils.toString(httpResponse.getEntity());
        getDebug(responseAsString);
        return responseAsString;
    }

}
