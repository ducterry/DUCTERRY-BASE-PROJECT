package com.ducterry.base.controller.email;

import com.ducterry.base.dto.auth.req.LoginRq;
import com.ducterry.base.dto.base.ResponseObject;
import com.ducterry.base.dto.email.request.AttachFileRq;
import com.ducterry.base.dto.email.request.SendEmailRq;
import com.ducterry.base.service.email.EmailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/mail/v1")
@Api(tags = "03. Email Controller", description = "Email API")
public class EmailController {

    private final Logger LOGGER = LogManager.getLogger(this.getClass());
    private final String PREFIX = "[EmailController]_";

    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }


    @ApiOperation(value = "Send Email với Template")
    @ResponseBody
    @PostMapping(value = "/send-email-template", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Thành công"),
            @ApiResponse(code = 300, message = "Không thành công"),
            @ApiResponse(code = 401, message = "Không tìm thấy kết quả"),
            @ApiResponse(code = 503, message = "Exception")})
    public ResponseObject<Object> sendEmailTemplate(@Valid @RequestBody SendEmailRq request) {
        LOGGER.debug(PREFIX + "sendEmailTemplate => {}", request);

        return new ResponseObject<>(true, this.emailService.sendEmailTemplate(request));
    }


    @ApiOperation(value = "Send Email với Attach File")
    @ResponseBody
    @PostMapping(value = "/send-email-attach-file", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Thành công"),
            @ApiResponse(code = 300, message = "Không thành công"),
            @ApiResponse(code = 401, message = "Không tìm thấy kết quả"),
            @ApiResponse(code = 503, message = "Exception")})
    public ResponseObject<Object> sendEmailAttachFile(@Valid @RequestBody AttachFileRq request) {
        LOGGER.debug(PREFIX + "sendEmailAttachFile => {}", request);

        return new ResponseObject<>(true, this.emailService.sendEmailAttachFile(request));
    }
}
