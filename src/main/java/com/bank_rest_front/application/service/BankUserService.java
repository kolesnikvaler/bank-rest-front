package com.bank_rest_front.application.service;

import com.bank_rest_front.application.api.BankApi;
import com.bank_rest_front.application.dto.UserDto;
import com.bank_rest_front.application.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BankUserService {
    private static final Logger log = LoggerFactory.getLogger(BankUserService.class);

    private final BankApi bankApi;

    public BankUserService(BankApi bankApi) {
        this.bankApi = bankApi;
    }

    public List<UserDto> getBankUsers(User currentUser) {
        try {
            return bankApi.getUsers(currentUser);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ArrayList<>(0);
        }
    }

}
