package com.phiz.userservice.controller;

import com.phiz.userservice.dto.auth.LoginRequest;
import com.phiz.userservice.dto.search.SearchParam;
import com.phiz.userservice.dto.user.User;
import com.phiz.userservice.exception.PhizException;
import com.phiz.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value = UserController.USER_BASE_PATH)
public class UserController {

    public static final String USER_BASE_PATH = "/user";

    private final UserService userService;

    @SneakyThrows
    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> register(@RequestBody User user) {

        if (Objects.isNull(user.getPhoneNumber()) || StringUtils.isEmpty(user.getPhoneNumber())) {

            throw new PhizException.Builder("Phone number is required").withStatus(HttpStatus.BAD_REQUEST).build();
        }

        if (userService.checkUserExist(SearchParam.builder().phoneNumber(user.getPhoneNumber()).build())) {

            throw new PhizException.Builder("Phone number is existing").withStatus(HttpStatus.BAD_REQUEST).build();

        }

        return ResponseEntity.ok(userService.register(user));
    }

    @SneakyThrows
    @GetMapping(value = "/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> fetchUserByUsername(@PathVariable String username) {

        return ResponseEntity.ok(userService.fetchUserByUsername(username));
    }

    @SneakyThrows
    @PostMapping(value = "/checkUserExist", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> checkUserExist(@RequestBody SearchParam param) {

        boolean checkExist = userService.checkUserExist(param);

        return ResponseEntity.ok(checkExist);
    }

    @SneakyThrows
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> updateUser(@PathVariable String id, @RequestBody User user) {


        return ResponseEntity.ok(userService.updateUser(id, user));
    }

    @PostMapping(value = "/forgotPassword", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> forgotPassword(@RequestParam String email) {

        return ResponseEntity.ok(userService.forgotPassword(email));
    }

    @SneakyThrows
    @PostMapping(value = "/changePassword", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> changePassword(@RequestBody LoginRequest loginRequest) {

        return ResponseEntity.ok(userService.changePassword(loginRequest));
    }


    @SneakyThrows
    @PostMapping(value = "/validateResetToken", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> validateResetToken(@RequestParam String resetToken) {

        return ResponseEntity.ok(userService.validateResetToken(resetToken));
    }

}
