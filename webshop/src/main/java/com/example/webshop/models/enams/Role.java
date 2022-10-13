package com.example.webshop.models.enams;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ROLE_USER,ROLE_ADMIN, ROLE_BUYER;

    @Override
    public String getAuthority() {
        return name();
    }
}
