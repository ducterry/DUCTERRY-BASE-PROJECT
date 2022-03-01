package com.ducterry.base.commons.config.security;

import com.ducterry.base.entity.login.User;
import com.ducterry.base.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userDetail = this.userRepository
                .findByUserName(username)
                .orElseThrow(
                        () -> new UsernameNotFoundException("User Not Found with -> username or email : " + username));
        return UserPrinciple.build(userDetail);
    }
}
