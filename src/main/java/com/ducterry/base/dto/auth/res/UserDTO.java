package com.ducterry.base.dto.auth.res;

import com.ducterry.base.entity.login.Role;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "userName",
        "passWord",
        "email",
        "accessToken",
        "tokenType",
        "roles"
})
public class UserDTO {
    private String userName;

    private String email;

    private String passWord;

    private Set<Role> roles = new HashSet<>();

    private String accessToken;

    private String tokenType;
}
