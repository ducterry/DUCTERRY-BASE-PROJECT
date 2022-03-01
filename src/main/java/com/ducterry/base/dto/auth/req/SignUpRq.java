package com.ducterry.base.dto.auth.req;


import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
@JsonPropertyOrder({
        "name", "username", "email", "password", "role"
})
public class SignUpRq {
    @NotBlank
    @Size(min = 3, max = 50)
    @ApiModelProperty(example = "DucTerry", notes = "Full Name", required = true)
    private String name;

    @NotBlank
    @Size(min = 3, max = 50)
    @ApiModelProperty(example = "duc.nd5", notes = "User Name", required = true)
    private String userName;

    @NotBlank
    @Size(max = 60)
    @Email
    @ApiModelProperty(example = "duc.nd5@gmail.com", notes = "Email", required = true)
    private String email;


    @ApiModelProperty(example = "[\"ROLE_USER\"]", notes = "Role", required = false)
    private Set<String> role;

    @NotBlank
    @Size(min = 6, max = 40)
    @ApiModelProperty(example = "abc13579", notes = "Password", required = true)
    private String passWord;

}
