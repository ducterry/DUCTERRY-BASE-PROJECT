package com.ducterry.base.helper.validation;

import com.ducterry.base.dto.auth.req.LoginRq;
import com.ducterry.base.dto.auth.req.SignUpRq;
import com.ducterry.base.dto.user.request.ChangePassRq;
import com.ducterry.base.dto.user.request.ChangeRoleRq;
import com.ducterry.base.entity.login.User;
import com.ducterry.base.enums.ErrorStatus;
import com.ducterry.base.exception.ApiException;
import com.ducterry.base.repository.UserRepository;
import com.ducterry.base.utils.StrUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class UserValidation {
    private final UserRepository userRepository;

    public UserValidation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void isRegisterValid(SignUpRq request) {
        Boolean isEmailExisted = userRepository.existsByEmail(request.getEmail());
        Boolean isUserNameExisted = userRepository.existsByUserName(request.getUserName());

        if (isEmailExisted || isUserNameExisted) {
            throw new ApiException(HttpStatus.BAD_REQUEST, ErrorStatus.INVALID_USER_EXISTED);
        }
    }

    public User isLoginValid(LoginRq request) {
        User userExisted = this.userRepository
                .findByUserName(request.getUserName())
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, ErrorStatus.USER_NOT_FOUND));
        if (!StrUtils.checkPass(request.getPassWord(), userExisted.getPassWord())) {
            throw new ApiException(HttpStatus.BAD_REQUEST, ErrorStatus.LOGIN_PASSWORD_INVALID);
        }

        return userExisted;
    }

    public User isChangePassValid(ChangePassRq request) {
        if (request.getOldPass().equals(request.getNewPass())) {
            throw new ApiException(HttpStatus.BAD_REQUEST, ErrorStatus.INVALID_PASS_DUPLICATE);
        }

        User userExisted = this.userRepository
                .findByUserName(request.getUserName())
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, ErrorStatus.USER_NOT_FOUND));

        if (!StrUtils.checkPass(request.getOldPass(), userExisted.getPassWord())) {
            throw new ApiException(HttpStatus.BAD_REQUEST, ErrorStatus.LOGIN_PASSWORD_INVALID);
        }

        return userExisted;
    }

    public User isChangeRoleValid(ChangeRoleRq request) {
        User userExisted = this.userRepository
                .findByUserName(request.getUserName())
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, ErrorStatus.USER_NOT_FOUND));

        if (request.getRole().isEmpty()){
            throw new ApiException(HttpStatus.BAD_REQUEST, ErrorStatus.CHANGE_ROLE_INVALID);
        }
        return userExisted;
    }
}
