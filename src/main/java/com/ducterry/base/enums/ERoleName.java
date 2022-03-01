package com.ducterry.base.enums;

import com.ducterry.base.exception.ApiException;
import org.springframework.http.HttpStatus;

public enum ERoleName {
    ROLE_USER("ROLE_USER"),
    ROLE_PM("ROLE_PM"),
    ROLE_ADMIN("ROLE_ADMIN");

    String roleName;

    ERoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }

    public static ERoleName getRoleName(String name) {
        for (ERoleName type : ERoleName.values()) {
            if (name.equals(type.getRoleName())) {
                return type;
            }
        }
        throw new ApiException(HttpStatus.BAD_REQUEST,ErrorStatus.INVALID_ROLE);
    }
}

