package com.bank_rest_front.application.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class User implements UserDetails {
    // MetaInfo
    private String lastName;
    private String firstName;
    private String token;

    // UserDetails
    private String username; // email
    private String password;
    private Collection<? extends GrantedAuthority> authorities;
    private Boolean accountExpired;
    private Boolean disabled;

    // Приватный конструктор — только Builder может создать объект
    private User(Builder builder) {
        this.lastName = builder.lastName;
        this.firstName = builder.firstName;
        this.token = builder.token;
        this.username = builder.username;
        this.password = builder.password;
        this.authorities = builder.authorities;
        this.accountExpired = builder.accountExpired;
        this.disabled = builder.disabled;
    }

    // Геттеры
    public String getLastName() { return lastName; }
    public String getFirstName() { return firstName; }
    public String getToken() { return token; }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return !Boolean.TRUE.equals(accountExpired);
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return !Boolean.TRUE.equals(disabled);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String lastName;
        private String firstName;
        private String token;
        private String username;
        private String password;
        private Collection<? extends GrantedAuthority> authorities;
        private Boolean accountExpired = false;
        private Boolean disabled = false;

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder token(String token) {
            this.token = token;
            return this;
        }

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder authorities(Collection<? extends GrantedAuthority> authorities) {
            this.authorities = authorities;
            return this;
        }

        public Builder accountExpired(Boolean accountExpired) {
            this.accountExpired = accountExpired;
            return this;
        }

        public Builder disabled(Boolean disabled) {
            this.disabled = disabled;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}