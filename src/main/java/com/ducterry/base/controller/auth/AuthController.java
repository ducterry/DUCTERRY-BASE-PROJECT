package com.ducterry.base.controller.auth;

import com.ducterry.base.dto.auth.req.LoginForm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.awt.*;
import java.util.Optional;

@RequestMapping("api/v1/")
@Api(tags = "Authen Controller", description = "Authen API")
public class AuthController {

    @ApiOperation(value = "API Login vào hệ thống")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Thành công"),
            @ApiResponse(code = 300, message = "Không thành công"),
            @ApiResponse(code = 401, message = "Không tìm thấy kết quả"),
            @ApiResponse(code = 503, message = "Exception")})
    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> login(@Valid @RequestBody LoginForm request) {
        return ResponseEntity
                .ok(HttpStatus.OK)
                .of(Optional.of("Thành Công"));
    }

}
