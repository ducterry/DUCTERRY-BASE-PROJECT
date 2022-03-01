package com.ducterry.base.dto.user.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
public class ChangeRoleRq {
    @NotBlank
    @Size(min = 3, max = 60)
    @ApiModelProperty(example = "duc.nd5", notes = "User Name", required = true)
    private String userName;

    @ApiModelProperty(example = "[\"ROLE_USER\", \"ROLE_PM\", \"ROLE_ADMIN\"]", notes = "Role", required = false)
    private Set<String> role;
}
