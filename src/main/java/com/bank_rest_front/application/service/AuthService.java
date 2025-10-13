package com.bank_rest_front.application.service;

import com.bank_rest_front.application.entity.User;
import com.bank_rest_front.application.utils.AppUrls;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.net.URI;
import java.util.List;

@Service
public class AuthService implements UserDetailsService {

    private final RestTemplate restTemplate;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final ObjectMapper mapper;

    public AuthService(RestTemplate restTemplate, JwtService jwtService, PasswordEncoder passwordEncoder, ObjectMapper mapper) {
        this.restTemplate = restTemplate;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.mapper = mapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String password = request.getParameter("password");

        return password == null || username == null ? null :
                authenticate(username, password);
    }

    public UserDetails authenticate(String userEmail, String password) {
        AuthRequest authRequest = new AuthRequest(userEmail, password);

        // Создаём RequestEntity
        RequestEntity<AuthRequest> request = RequestEntity
                .post(URI.create(AppUrls.bankAppUrl + "/auth/login"))
                .contentType(MediaType.APPLICATION_JSON)
                .body(authRequest);

        // Отправляем запрос
        ResponseEntity<AuthResponse> response = restTemplate.exchange(request, AuthResponse.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            AuthResponse authResponse = response.getBody();

            String token = authResponse.token;
            try {
                JsonNode jsonNodeHeader = jwtService.readHeader(token);
                JsonParser traverse = jsonNodeHeader.traverse();
                JwtHeaders jwtHeaders = mapper.readValue(traverse, JwtHeaders.class);

                System.out.printf("User auth: %s %s%n", jwtHeaders.last_name, jwtHeaders.first_name);
                System.out.printf("Role: %s%n", jwtHeaders.role);
                return User.builder()
                        .lastName(jwtHeaders.last_name())
                        .firstName(jwtHeaders.first_name())
                        .token(token)
                        .username(userEmail)
                        .password(passwordEncoder.encode(password))
                        .authorities(List.of(new SimpleGrantedAuthority(jwtHeaders.role)))
                        .accountExpired(jwtHeaders.is_account_expired)
                        .disabled(!jwtHeaders.is_enabled)
                        .build();
            } catch (Exception e) {
                throw new RuntimeException("Error with parsing header and payload: " + e.getMessage());
            }
        }
        throw new RuntimeException("Authentication failed: " + response.getStatusCode());
    }

    private record AuthRequest(String email, String password) {}
    private record AuthResponse(String token) {}

    private record JwtHeaders (
            String last_name,
            String first_name,
            String role,
            Boolean is_account_expired,
            Boolean is_account_locked,
            Boolean is_enabled
    ) {}
}
//                return User.builder()
//                        .username(userEmail)
//                        .password(passwordEncoder.encode(password))
//                        .authorities(new SimpleGrantedAuthority(jwtHeaders.role))
//                        .accountExpired(jwtHeaders.is_account_expired)
//                        .disabled(!jwtHeaders.is_enabled)
//                        .build();
