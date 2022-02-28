package com.ducterry.base.commons.config.security;

import com.ducterry.base.entity.login.User;
import com.ducterry.base.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    ModelMapper modelMapper;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userDetail = this.userRepository
                .findByUsername(username)
                .orElseThrow(
                        () -> new UsernameNotFoundException("User Not Found with -> username or email : " + username));
        return this.modelMapper.map(userDetail, UserDetails.class);
    }
}