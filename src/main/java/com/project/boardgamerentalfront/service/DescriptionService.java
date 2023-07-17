package com.project.boardgamerentalfront.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class DescriptionService {
    private final RestTemplate restTemplate = new RestTemplate();
    private static DescriptionService descriptionService;

    private DescriptionService() {
    }

    public static DescriptionService getInstance() {
        if (descriptionService == null) {
            descriptionService = new DescriptionService();
        }
        return descriptionService;
    }

    public String changeLanguage(String description, String language) {
        if (language == null) {
            language = "PL";
        }
        ResponseEntity<String> rs = restTemplate.getForEntity(
                "http://localhost:8080/v1/description?language={language}&description={description}", String.class,
                language, description);
        return rs.getBody();
    }
}
