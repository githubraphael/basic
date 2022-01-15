package com.phiz.gateway.client;

import com.phiz.gateway.dto.auth.LoginRequest;
import com.phiz.gateway.dto.search.SearchParam;
import com.phiz.gateway.dto.user.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "${client.user.name}", url = "${client.user.url}")
public interface UserClient {

    @PostMapping(value = "/register")
    User register(@RequestBody User user);

    @PostMapping(value = "/checkUserExist")
    Boolean checkUserExist(@RequestBody SearchParam param);

    @PutMapping(value = "/{id}")
    User updateUser(@PathVariable String id, @RequestBody User user);

    @GetMapping(value = "/{username}")
    User fetchUserByUsername(@PathVariable String username);

    @PostMapping(value = "/validateResetToken")
    Boolean validateResetToken(@RequestParam String resetToken);

    @PostMapping(value = "/changePassword")
    User changePassword(@RequestBody LoginRequest loginRequest);

    @PostMapping(value = "/forgotPassword")
    User forgotPassword(@RequestParam String email);
}
