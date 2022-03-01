package com.ducterry.base.helper.mapper;

import com.ducterry.base.dto.auth.req.SignUpForm;
import com.ducterry.base.dto.auth.res.UserDTO;
import com.ducterry.base.entity.login.Role;
import com.ducterry.base.entity.login.User;
import com.ducterry.base.enums.ERoleName;
import com.ducterry.base.enums.ErrorStatus;
import com.ducterry.base.exception.ApiException;
import com.ducterry.base.repository.RoleRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserConvert {
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserConvert(RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User convertToEntity(SignUpForm request) {
        User entity = UserMapper.copyToEntity(request);
        entity.setPassword(passwordEncoder.encode(request.getPassword()));

        Set<String> strRoles = request.getRole();
        Set<Role> roles = new HashSet<>();
        if (strRoles.isEmpty()) {
            Role userRole = this.roleRepository
                    .findByName(ERoleName.ROLE_USER)
                    .orElseThrow(
                            () -> new ApiException(HttpStatus.BAD_REQUEST, ErrorStatus.INVALID_ROLE));

            roles.add(userRole);
        } else {
            strRoles.forEach(item -> {
                switch (ERoleName.getRoleName(item)) {
                    case ROLE_PM:
                        Role pmRole = this.roleRepository
                                .findByName(ERoleName.ROLE_PM)
                                .orElseThrow(
                                        () -> new ApiException(HttpStatus.BAD_REQUEST, ErrorStatus.INVALID_ROLE));

                        roles.add(pmRole);
                        break;
                    case ROLE_ADMIN:
                        Role adminRole = this.roleRepository
                                .findByName(ERoleName.ROLE_ADMIN)
                                .orElseThrow(
                                        () -> new ApiException(HttpStatus.BAD_REQUEST, ErrorStatus.INVALID_ROLE));

                        roles.add(adminRole);
                        break;
                    default:
                        Role userRole = this.roleRepository
                                .findByName(ERoleName.ROLE_USER)
                                .orElseThrow(
                                        () -> new ApiException(HttpStatus.BAD_REQUEST, ErrorStatus.INVALID_ROLE));

                        roles.add(userRole);

                }
            });
        }
        entity.setRoles(roles);

        return entity;
    }

    public UserDTO convertToDTO(User entity) {
        UserDTO dto = new UserDTO();
        BeanUtils.copyProperties(entity, dto);

        String password = entity.getPassword();


        return dto;
    }
}
