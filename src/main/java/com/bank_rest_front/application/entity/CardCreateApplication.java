package com.bank_rest_front.application.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardCreateApplication {
    public UUID applicationId;
    public LocalDateTime dateCreate;
    public String userEmail;
}
