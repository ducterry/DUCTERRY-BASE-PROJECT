package com.ducterry.base.helper.mapper;

import com.ducterry.base.dto.auth.req.SignUpForm;
import com.ducterry.base.entity.login.User;
import com.ducterry.base.utils.StrUtils;
import org.springframework.beans.BeanUtils;

public class UserMapper {
    public static User copyToEntity(SignUpForm request) {
        User entity = new User();
        BeanUtils.copyProperties(request, entity);
        return entity;
    }
}
