package com.trans.todos.api.v1.services;

import com.trans.todos.api.v1.model.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;

    public UserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {

        UserDTO userDTO = userService.findByName(login);

        log.info("try loging user:" + login);

        if(userDTO == null)
            throw new UsernameNotFoundException(login);

        log.info("try loging 2 user:" + login + ": " + userDTO.getLogin() + ": " + userDTO.getPassword());

        List<SimpleGrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority("user"));

        return new User(userDTO.getLogin(), userDTO.getPassword(), authorities);

    }
}
