package com.ducterry.base.service.user;

import com.ducterry.base.dto.auth.res.UserDTO;
import com.ducterry.base.dto.user.request.ChangePassRq;
import com.ducterry.base.entity.login.User;
import com.ducterry.base.helper.mapper.UserConvert;
import com.ducterry.base.helper.support.UserSupport;
import com.ducterry.base.helper.validation.UserValidation;
import com.ducterry.base.repository.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final Logger LOGGER = LogManager.getLogger(this.getClass());
    private final String PREFIX = "[UserService]_";

    private final UserValidation userValidation;
    private final UserConvert userConvert;
    private final UserSupport userSupport;
    private final UserRepository userRepository;

    public UserService(UserValidation userValidation, UserConvert userConvert, UserSupport userSupport, UserRepository userRepository) {
        this.userValidation = userValidation;
        this.userConvert = userConvert;
        this.userSupport = userSupport;
        this.userRepository = userRepository;
    }

    public UserDTO changePass(ChangePassRq request) {
        try {
            LOGGER.debug(PREFIX + "changePass => {}", request);

            // 01. Validation
            User userExisted = this.userValidation.isChangePassValid(request);

            // 02. Mapping New Password
            User userChanged = this.userConvert.convertToEntity(userExisted, request);
            User userSaved = this.userRepository.save(userChanged);

            // 03. Convert to DTO
            return this.userConvert.convertToDTO(userSaved);
        } catch (Exception ex) {
            throw ex;
        }

    }
}
