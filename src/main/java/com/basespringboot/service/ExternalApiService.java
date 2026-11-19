package com.basespringboot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
@RequiredArgsConstructor
public class ExternalApiService {

    private final RestClient restClient;

    // Exemple 1 : Récupérer la réponse brute en JSON (String)
    public String getExternalData(String url) {
        return restClient.get()
                .uri(url)
                .retrieve()
                .body(String.class);
    }

    // Exemple 2 : Convertir directement le JSON reçu en un objet Java (DTO)
    // Utile si l'API externe renvoie un format connu (ex: {"title": "...", "body": "..."})
    public <T> T getExternalObject(String url, Class<T> responseType) {
        return restClient.get()
                .uri(url)
                .retrieve()
                .body(responseType);
    }
}