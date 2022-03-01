package com.ducterry.base.controller.user;

import com.ducterry.base.custom.RequestLogger;
import com.ducterry.base.dto.auth.res.UserDTO;
import com.ducterry.base.dto.base.ResponseObject;
import com.ducterry.base.dto.user.request.ChangePassRq;
import com.ducterry.base.service.user.UserService;
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
@RequestMapping("/api/user/v1")
@Api(tags = "02. User Controller", description = "User API")
public class UserController {
    private final Logger LOGGER = LogManager.getLogger(this.getClass());
    private final String PREFIX = "[UserController]_";

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @RequestLogger
    @ApiOperation(value = "Thay đổi Password")
    @ResponseBody
    @PostMapping(value = "/change-pass", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Thành công"),
            @ApiResponse(code = 300, message = "Không thành công"),
            @ApiResponse(code = 401, message = "Không tìm thấy kết quả"),
            @ApiResponse(code = 503, message = "Exception")})
    public ResponseObject<UserDTO> changePass(@Valid @RequestBody ChangePassRq request) {
        LOGGER.debug(PREFIX + "changePass => {}", request);

        return new ResponseObject<>(true, this.userService.changePass(request));
    }
}
