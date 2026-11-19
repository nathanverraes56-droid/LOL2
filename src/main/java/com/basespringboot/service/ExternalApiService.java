package com.basespringboot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
@RequiredArgsConstructor
public class ExternalApiService {

    private final RestClient restClient;

    // Exemple 1 : Get the answer in JSON (String) format.
    public String getExternalData(String url) {
        return restClient.get()
                .uri(url)
                .retrieve()
                .body(String.class);
    }

    // Exemple 2 : Transform the JSON in a Java Object (DTO) 
    // Uselful if the externe API return a known format (ex: {"title": "...", "body": "..."})
    public <T> T getExternalObject(String url, Class<T> responseType) {
        return restClient.get()
                .uri(url)
                .retrieve()
                .body(responseType);
    }
}