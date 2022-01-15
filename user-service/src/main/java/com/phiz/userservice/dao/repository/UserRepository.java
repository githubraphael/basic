package com.phiz.userservice.dao.repository;

import com.phiz.userservice.dao.entity.UserEntity;
import com.phiz.userservice.dto.user.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<UserEntity, String> {

    UserEntity findByUsername(String username);

    UserEntity findByPhoneNumber(String phoneNumber);

    UserEntity findByEmail(String email);

    UserEntity findByResetToken(String resetToken);
}
