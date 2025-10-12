package com.bank_rest_front.application.utils;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;

public final class SecurityUtils {
    private SecurityUtils() {}

    public static boolean hasAnyRole(UserDetails user, String... roles) {
        return Arrays.stream(roles)
                .anyMatch(role -> user.getAuthorities().stream()
                        .anyMatch(auth ->
                                auth.getAuthority().equals(role) ||
                                        auth.getAuthority().equals("ROLE_" + role)));
    }
}

