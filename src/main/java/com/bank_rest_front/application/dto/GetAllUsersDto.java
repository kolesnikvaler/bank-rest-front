package com.bank_rest_front.application.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record GetAllUsersDto(List<UserDto> users) {
}
