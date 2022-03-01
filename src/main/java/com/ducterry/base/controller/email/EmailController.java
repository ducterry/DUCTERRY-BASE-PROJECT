package com.ducterry.base.controller.email;

import com.ducterry.base.dto.auth.req.LoginForm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/mail/v1")
@Api(tags = "02. Email Controller", description = "Email API")
public class EmailController {


    @ApiOperation(value = "API Send Email với Template" )
    @ResponseBody
    @PostMapping(value = "/send-email", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Thành công"),
            @ApiResponse(code = 300, message = "Không thành công"),
            @ApiResponse(code = 401, message = "Không tìm thấy kết quả"),
            @ApiResponse(code = 503, message = "Exception")})
    public ResponseEntity<Object> sendEmail(@Valid @RequestBody LoginForm request) {
        return ResponseEntity
                .ok(HttpStatus.OK)
                .of(Optional.of("Thành Công"));
    }

}
