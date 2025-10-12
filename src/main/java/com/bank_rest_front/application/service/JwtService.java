package com.bank_rest_front.application.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class JwtService {
    private final ObjectMapper objectMapper;

    public JwtService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public JsonNode readHeader(String token) throws Exception {
        String[] chunks = token.split("\\.");
        if (chunks.length < 1) throw new IllegalArgumentException("Invalid JWT");
        return parseJson(decodeBase64Url(chunks[0]));
    }

    public JsonNode readPayload(String token) throws Exception {
        String[] chunks = token.split("\\.");
        if (chunks.length < 2) throw new IllegalArgumentException("Invalid JWT");
        return parseJson(decodeBase64Url(chunks[1]));
    }

    private String decodeBase64Url(String encoded) {
        // Base64Url → Base64
        String padded = encoded.replace('-', '+').replace('_', '/');
        switch (padded.length() % 4) {
            case 2 -> padded += "==";
            case 3 -> padded += "=";
        }
        byte[] decoded = Base64.getDecoder().decode(padded);
        return new String(decoded);
    }

    private JsonNode parseJson(String json) throws Exception {
        return objectMapper.readTree(json);
    }
}
