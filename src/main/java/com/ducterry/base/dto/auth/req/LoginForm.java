package com.ducterry.base.dto.auth.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class LoginForm {
    @NotBlank
    @Size(min=3, max = 60)
    @ApiModelProperty(example = "duc.nd5", notes = "User Name", required = true)
    private String username;

    @NotBlank
    @Size(min = 6, max = 40)
    @ApiModelProperty(example = "abc13579", notes = "Password", required = true)
    private String password;

}