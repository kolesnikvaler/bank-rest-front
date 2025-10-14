package com.bank_rest_front.application.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Card {
    public LocalDateTime dateCreate;
    public String number;
    public LocalDate expiryDate;
    public Short cvvCode;
    public Double balance;
    public String status;
}
