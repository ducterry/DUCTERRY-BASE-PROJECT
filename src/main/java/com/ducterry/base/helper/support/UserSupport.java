package com.ducterry.base.helper.support;

import com.ducterry.base.dto.auth.req.LoginForm;
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


    public  Authentication getAuthentication(LoginForm request) {
        Authentication authentication = this.authentication.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return authentication;
    }
}
