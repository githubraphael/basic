package com.phiz.gateway.service;

import com.phiz.gateway.dto.auth.LoginRequest;
import com.phiz.gateway.dto.search.SearchParam;
import com.phiz.gateway.dto.user.User;

public interface UserService {


    User register(User user);

    Boolean checkUserExist(SearchParam param);

    User updateUser(String id, User user);

    Boolean validateResetToken(String resetToken);

    User changePassword(LoginRequest loginRequest);

    User forgotPassword(String email);

}
