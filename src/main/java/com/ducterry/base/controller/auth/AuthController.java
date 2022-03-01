package com.ducterry.base.controller.auth;

import com.ducterry.base.custom.RequestLogger;
import com.ducterry.base.dto.auth.req.LoginRq;
import com.ducterry.base.dto.auth.req.SignUpRq;
import com.ducterry.base.dto.auth.res.UserDTO;
import com.ducterry.base.dto.base.ResponseObject;
import com.ducterry.base.service.auth.AuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/authen/v1")
@Api(tags = "01. Authen Controller", description = "Authentication API")
public class AuthController {
    private final Logger LOGGER = LogManager.getLogger(this.getClass());
    private final String PREFIX = "[AuthController]_";

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }


    @RequestLogger
    @ApiOperation(value = "Login vào hệ thống")
    @ResponseBody
    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Thành công"),
            @ApiResponse(code = 300, message = "Không thành công"),
            @ApiResponse(code = 401, message = "Không tìm thấy kết quả"),
            @ApiResponse(code = 503, message = "Exception")})
    public ResponseObject<UserDTO> login(@Valid @RequestBody LoginRq request) {
        LOGGER.debug(PREFIX + "login => {}", request);

        return new ResponseObject<>(true, this.authService.login(request));
    }


    @RequestLogger
    @ApiOperation(value = "Register vào hệ thống")
    @ResponseBody
    @PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Thành công"),
            @ApiResponse(code = 300, message = "Không thành công"),
            @ApiResponse(code = 401, message = "Không tìm thấy kết quả"),
            @ApiResponse(code = 503, message = "Exception")})
    public ResponseObject<UserDTO> register(@Valid @RequestBody SignUpRq request) {
        LOGGER.debug(PREFIX + "register => {}", request);

        return new ResponseObject<>(true, this.authService.register(request));

    }

}
