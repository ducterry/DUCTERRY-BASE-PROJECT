package com.ducterry.base.service.auth;

import com.ducterry.base.commons.config.security.UserPrinciple;
import com.ducterry.base.commons.config.security.jwt.JwtProvider;
import com.ducterry.base.commons.constant.AuthConstants;
import com.ducterry.base.dto.auth.req.LoginRq;
import com.ducterry.base.dto.auth.req.SignUpRq;
import com.ducterry.base.dto.auth.res.UserDTO;
import com.ducterry.base.entity.login.User;
import com.ducterry.base.helper.mapper.UserConvert;
import com.ducterry.base.helper.support.UserSupport;
import com.ducterry.base.helper.validation.UserValidation;
import com.ducterry.base.repository.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final Logger LOGGER = LogManager.getLogger(this.getClass());
    private final String PREFIX = "[AuthService]_";

    private final UserRepository userRepository;
    private final UserValidation userValidation;
    private final UserConvert userConvert;
    private final JwtProvider jwtProvider;
    private final UserSupport userSupport;

    public AuthService(UserRepository userRepository, UserValidation userValidation, UserConvert userConvert, JwtProvider jwtProvider, UserSupport userSupport) {
        this.userRepository = userRepository;
        this.userValidation = userValidation;
        this.userConvert = userConvert;
        this.jwtProvider = jwtProvider;
        this.userSupport = userSupport;
    }

    public UserDTO register(SignUpRq request) {
        try {
            LOGGER.debug(PREFIX + "register => {}", request);

            // 01. Validation
            this.userValidation.isRegisterValid(request);


            // 02. Convert To Entity
            User entity = this.userConvert.register(request);
            User userSaved = this.userRepository.save(entity);


            // 03. Convert To DTO
            return this.userConvert.convertToDTO(userSaved);
        } catch (Exception ex) {
            throw ex;
        }

    }

    public UserDTO login(LoginRq request) {
        try {
            // 01. Validation
            User userExisted = this.userValidation.isLoginValid(request);


            // 02. Tạo Token
            Authentication authentication = this.userSupport.getAuthentication(request);
            UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();


            String token = this.jwtProvider.generateToken(userPrinciple);

            // 03. Convert to DTO
            UserDTO dto = this.userConvert.convertToDTO(userExisted);
            dto.setAccessToken(token);
            dto.setTokenType(AuthConstants.BEARER);


            return dto;
        } catch (Exception ex) {
            throw ex;
        }
    }
}
