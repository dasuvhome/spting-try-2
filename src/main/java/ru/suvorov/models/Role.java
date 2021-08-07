package ru.suvorov.models;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER, ADMINISTRATOR, REDACTOR;

    @Override
    public String getAuthority() {
        return name();
    }
}
