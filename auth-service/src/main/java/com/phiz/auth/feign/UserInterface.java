package com.phiz.auth.feign;

import com.phiz.auth.constants.MicroServiceConstants;
import com.phiz.auth.dto.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.phiz.auth.constants.MicroServiceConstants.BASE_API;
import static com.phiz.auth.constants.MicroServiceConstants.USER_MICROSERVICE;

@FeignClient(name = USER_MICROSERVICE)
@Service
@RequestMapping(value = BASE_API)
public interface UserInterface {

    @RequestMapping(value = MicroServiceConstants.UserMicroServiceConstants.FETCH_USER_BY_USERNAME)
    User findByUsername(String username);
}
