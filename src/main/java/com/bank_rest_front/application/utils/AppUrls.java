package com.bank_rest_front.application.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AppUrls {

    public static String bankAppUrl;

    @Value("${bank-app.url}")
    public void setBankAppUrl(String url) {
        AppUrls.bankAppUrl = url;
    }
}
