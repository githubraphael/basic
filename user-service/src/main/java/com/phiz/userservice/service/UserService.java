package com.phiz.userservice.service;

import com.phiz.userservice.dto.auth.LoginRequest;
import com.phiz.userservice.dto.search.SearchParam;
import com.phiz.userservice.dto.user.User;

public interface UserService {

    User register(User user);

    User fetchUserByUsername(String username);

    Boolean checkUserExist(SearchParam param);

    User updateUser(String id, User user);

    User forgotPassword(String email);

    User changePassword(LoginRequest loginRequest);

    Boolean validateResetToken(String resetToken);
}
