package com.bank_rest_front.application.api;

import com.bank_rest_front.application.dto.GetAllUsersDto;
import com.bank_rest_front.application.dto.UserDto;
import com.bank_rest_front.application.entity.User;
import com.bank_rest_front.application.utils.AppUrls;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Component
public class BankApi {

    private final RestTemplate restTemplate;

    public BankApi(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<UserDto> getUsers(User currentUser) {
        // Создаём RequestEntity
        RequestEntity<Void> request = RequestEntity
                .get(URI.create(AppUrls.bankAppUrl + "/users"))
                .header(HttpHeaders.AUTHORIZATION, currentUser.getBearerToken())
                .build();

        // Отправляем запрос
        ResponseEntity<GetAllUsersDto> response = restTemplate.exchange(request, GetAllUsersDto.class);
        System.out.println(response.getBody());
        return response.getStatusCode() == HttpStatus.OK && response.getBody() != null && response.getBody().users() != null ?
                response.getBody().users() :
                new ArrayList<>(0);
    }
}
