package com.ducterry.base.helper.support;

import com.ducterry.base.dto.auth.req.LoginRq;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.security.authentication.AuthenticationManager;

@Service
public class UserSupport {
    private final AuthenticationManager authentication;

    public UserSupport(AuthenticationManager authentication) {
        this.authentication = authentication;
    }


    public  Authentication getAuthentication(LoginRq request) {
        Authentication authentication = this.authentication.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassWord()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return authentication;
    }
}
