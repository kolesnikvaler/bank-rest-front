package com.bank_rest_front.application.dto;

import com.bank_rest_front.application.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetAllUsersDto {
    public List<User> users;
}
