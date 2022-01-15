package com.phiz.userservice.util;

import com.phiz.userservice.dao.entity.UserEntity;
import com.phiz.userservice.dto.user.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Mapper {

    private final ModelMapper modelMapper;

    @Autowired
    public Mapper(ModelMapper modelMapper) {

        this.modelMapper = modelMapper;
    }

    public User toUser(UserEntity entity) {

        return modelMapper.map(entity, User.class);
    }

    public UserEntity toUserEntity(User user) {

        return modelMapper.map(user, UserEntity.class);
    }
}
