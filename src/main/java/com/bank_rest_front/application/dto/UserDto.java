package com.bank_rest_front.application.dto;

import com.bank_rest_front.application.entity.enums.Role;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDate;
import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public record UserDto(
        LocalDateTime dateCreate,
        String lastName,
        String firstName,
        String login,
        String email,
        Role role,
        LocalDate accountExpiryDate,
        Boolean isAccountLocked,
        Boolean isEnabled
) {
}
