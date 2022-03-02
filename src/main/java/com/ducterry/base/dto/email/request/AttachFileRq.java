package com.ducterry.base.dto.email.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "subject", "toEmail", "body", "message"
})
public class AttachFileRq {

    @NotBlank
    @Size(min = 3, max = 60)
    @ApiModelProperty(example = "dev.ndangduc.bn@gmail.com", notes = "To Email", required = true)
    private String toEmail;

    @NotBlank
    @Size(min = 3, max = 60)
    @ApiModelProperty(example = "This is body Email", notes = "Content Body", required = true)
    private String body;

    @NotBlank
    @Size(min = 3, max = 60)
    @ApiModelProperty(example = "V/v: Test nội dung gửi email", notes = "Title Subject", required = true)
    private String subject;


    private String attachFile;

    private String message;
}
