package com.phiz.gateway.controller;

import com.phiz.gateway.config.JwtTokenUtil;
import com.phiz.gateway.dto.auth.LoginRequest;
import com.phiz.gateway.dto.auth.LoginResponse;
import com.phiz.gateway.dto.search.SearchParam;
import com.phiz.gateway.dto.user.User;
import com.phiz.gateway.service.UserService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Slf4j
@RestController
@RequestMapping(value = "api/user")
public class UserController {

    private final UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsService jwtInMemoryUserDetailsService;

    public UserController(UserService userService) {

        this.userService = userService;
    }

    @PostMapping(value = "/register")
    public User register(@RequestBody User user) {

        return userService.register(user);
    }

    @PostMapping(value = "/checkUserExist")
    public Boolean checkUserExist(@RequestBody SearchParam param) {

        return userService.checkUserExist(param);
    }

    @PutMapping(value = "/{id}")
    public User updateUser(@PathVariable String id, @RequestBody User user) {

        return userService.updateUser(id, user);
    }


    @PostMapping(value = "/validateResetToken")
    Boolean validateResetToken(@RequestParam String resetToken) {

        return userService.validateResetToken(resetToken);
    }

    @PostMapping(value = "/changePassword")
    User changePassword(@RequestBody LoginRequest loginRequest) {

        return userService.changePassword(loginRequest);
    }

    @PostMapping(value = "/forgotPassword")
    User forgotPassword(@RequestParam String email) {

        return userService.forgotPassword(email);
    }

    @SneakyThrows
    @PostMapping(value = "/login/username", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest login) {

        authenticate(login.getUsername(), login.getPassword());
        final UserDetails userDetails = jwtInMemoryUserDetailsService
                .loadUserByUsername(login.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(LoginResponse.builder()
                .token(token)
                .build());
    }

    private void authenticate(String username, String password) throws Exception {

        Objects.requireNonNull(username);
        Objects.requireNonNull(password);

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }

    }

}
