package com.phiz.auth.security;

import com.phiz.auth.feign.UserInterface;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Objects;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    private UserInterface userInterface;

    public CustomUserDetailsService(UserInterface userInterface) {

        this.userInterface = userInterface;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        com.phiz.auth.dto.User user = userInterface.findByUsername(username);

        if (Objects.nonNull(user)) {
            return new User(user.getUsername(), user.getPassword(), new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }
}
