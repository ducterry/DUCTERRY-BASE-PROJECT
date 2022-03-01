package com.ducterry.base.dto.user.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class ChangePassRq {
    @NotBlank
    @Size(min=3, max = 60)
    @ApiModelProperty(example = "duc.nd5", notes = "User Name", required = true)
    private String userName;


    @NotBlank
    @Size(min=3, max = 60)
    @ApiModelProperty(example = "abc13579", notes = "Old Pass", required = true)
    private String oldPass;


    @NotBlank
    @Size(min=3, max = 60)
    @ApiModelProperty(example = "abc123", notes = "New Pass", required = true)
    private String newPass;
}
