package com.phiz.userservice.service.impl;

import com.phiz.userservice.dao.entity.UserEntity;
import com.phiz.userservice.dao.repository.UserRepository;
import com.phiz.userservice.dto.auth.LoginRequest;
import com.phiz.userservice.dto.search.SearchParam;
import com.phiz.userservice.dto.user.User;
import com.phiz.userservice.exception.PhizException;
import com.phiz.userservice.service.EmailSenderService;
import com.phiz.userservice.service.UserService;
import com.phiz.userservice.util.Mapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final Mapper mapper;
    private final PasswordEncoder passwordEncoder;
    private final EmailSenderService emailSenderService;


    @Override
    public User register(User user) {

        try {

            user.setPassword(passwordEncoder.encode(user.getPassword()));
            UserEntity userEntity = userRepository.save(mapper.toUserEntity(user));
            user = mapper.toUser(userEntity);

        } catch (DuplicateKeyException e) {
            throw new PhizException.Builder(e.getMessage()).withStatus(HttpStatus.BAD_REQUEST).build();
        }

        return user;
    }

    @Override
    public User fetchUserByUsername(String username) {

        UserEntity userEntity = userRepository.findByUsername(username);

        if (Objects.isNull(userEntity)) {
            new PhizException.Builder("User not found").withStatus(HttpStatus.NOT_FOUND).build();
        }

        return mapper.toUser(userEntity);
    }

    @Override
    public Boolean checkUserExist(SearchParam param) {

        UserEntity userEntity = userRepository.findByPhoneNumber(param.getPhoneNumber());
        if (Objects.isNull(userEntity)) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    @Override
    public User updateUser(String id, User user) {

        Optional<UserEntity> userEntity = userRepository.findById(id);

        if (userEntity.isPresent()) {

            UserEntity entity = mapper.toUserEntity(user);
            entity.setId(id);
            entity.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(entity);
        }

        return user;
    }

    @Override
    public User forgotPassword(String email) {

        UserEntity userEntity = userRepository.findByEmail(email);
        log.info("User found {} ", Objects.isNull(userEntity));
        if (Objects.isNull(userEntity)) {
            throw new PhizException.Builder("User not found").withStatus(HttpStatus.NOT_FOUND).build();
        }

        userEntity.setResetToken(UUID.randomUUID().toString());
        userEntity.setModified(Instant.now());
        userRepository.save(userEntity);

        User user = mapper.toUser(userEntity);
        sendEmail(user);
        return user;
    }

    @Override
    public User changePassword(LoginRequest loginRequest) {

        UserEntity userEntity = userRepository.findByUsername(loginRequest.getUsername());

        if (Objects.isNull(userEntity)) {
            throw new PhizException.Builder("User not found").withStatus(HttpStatus.NOT_FOUND).build();
        }

        userEntity.setPassword(passwordEncoder.encode(loginRequest.getNewPassword()));
        userEntity.setModified(Instant.now());
        userRepository.save(userEntity);

        return mapper.toUser(userEntity);
    }

    @Override
    public Boolean validateResetToken(String resetToken) {

        UserEntity userEntity = userRepository.findByResetToken(resetToken);

        if (Objects.isNull(userEntity)) {
            throw new PhizException.Builder("Invalid Reset Token").withStatus(HttpStatus.NOT_FOUND).build();
        }

        Long diff = ChronoUnit.MINUTES.between(userEntity.getModified(), Instant.now());
        Boolean isValid = diff <= 15 ? Boolean.TRUE : Boolean.FALSE;
        return isValid;
    }

    private void sendEmail(User user) {

        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();

            mailMessage.setTo(user.getEmail());
            mailMessage.setSubject("Complete Password Reset!");
            mailMessage.setFrom("phizlive2021@gmail.com");
            mailMessage.setText("To complete the password reset process, please click here: "
                    + "http://localhost:7001/user/validateResetToken?resetToken=" + user.getResetToken());

            emailSenderService.sendEmail(mailMessage);
        } catch (Exception e) {
            log.info("Error in email sending {} ", e.getMessage());
        }

    }
}