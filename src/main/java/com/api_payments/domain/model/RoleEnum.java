package com.api_payments.domain.model;

import lombok.Getter;

@Getter
public enum RoleEnum {
    USER("user"),
    ADMIN("admin");
    private String role;

    RoleEnum(String role){
        this.role =role;
    }
}
