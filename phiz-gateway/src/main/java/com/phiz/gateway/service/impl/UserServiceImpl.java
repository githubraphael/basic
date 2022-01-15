package com.phiz.gateway.service.impl;

import com.phiz.gateway.client.UserClient;
import com.phiz.gateway.dto.auth.LoginRequest;
import com.phiz.gateway.dto.search.SearchParam;
import com.phiz.gateway.dto.user.User;
import com.phiz.gateway.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserClient userClient;


    public UserServiceImpl(UserClient userClient) {

        this.userClient = userClient;
    }


    @Override
    public User register(User user) {

        return userClient.register(user);
    }

    @Override
    public Boolean checkUserExist(SearchParam param) {

        return userClient.checkUserExist(param);
    }

    @Override
    public User updateUser(String id, User user) {

        return userClient.updateUser(id, user);
    }

    @Override
    public Boolean validateResetToken(String resetToken) {

        return userClient.validateResetToken(resetToken);
    }

    @Override
    public User changePassword(LoginRequest loginRequest) {

        return userClient.changePassword(loginRequest);
    }

    @Override
    public User forgotPassword(String email) {

        return userClient.forgotPassword(email);
    }
}
