package com.ducterry.base.helper.mapper;

import com.ducterry.base.dto.auth.req.SignUpRq;
import com.ducterry.base.entity.login.User;
import org.springframework.beans.BeanUtils;

public class UserMapper {
    public static User copyToEntity(SignUpRq request) {
        User entity = new User();
        BeanUtils.copyProperties(request, entity);
        return entity;
    }
}
