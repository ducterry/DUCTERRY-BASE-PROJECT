package com.ducterry.base.helper.validation;

import com.ducterry.base.dto.auth.req.LoginForm;
import com.ducterry.base.dto.auth.req.SignUpForm;
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


    public void isRegisterValid(SignUpForm request) {
        Boolean isEmailExisted = userRepository.existsByEmail(request.getEmail());
        Boolean isUserNameExisted = userRepository.existsByUsername(request.getUsername());

        if (isEmailExisted || isUserNameExisted) {
            throw new ApiException(HttpStatus.BAD_REQUEST, ErrorStatus.INVALID_USER_EXISTED);
        }
    }

    public User isLoginValid(LoginForm request) {
        User userExisted = this.userRepository
                .findByUsername(request.getUsername())
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, ErrorStatus.USER_NOT_FOUND));
        if (!StrUtils.checkPass(request.getPassword(), userExisted.getPassword())) {
            throw new ApiException(HttpStatus.BAD_REQUEST, ErrorStatus.LOGIN_PASSWORD_INVALID);
        }

        return userExisted;
    }
}
