package com.chess.players.util;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

@Component
public class HttpRequestUtil {

    @Autowired
    private RestTemplate restTemplate;

    public ResponseEntity get(String endpoint) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = null;
        try {
            response = restTemplate.exchange(endpoint, HttpMethod.GET, entity, String.class);
        } catch (HttpStatusCodeException e) {
            e.printStackTrace();
        }
        return response;
    }
}
