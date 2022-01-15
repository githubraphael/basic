package com.phiz.auth.config;

import com.phiz.auth.feign.UserInterface;
import com.phiz.auth.security.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@PropertySource("classpath:security.properties")
public class AuthConfiguration {

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public UserDetailsService jwtUserDetailsService(UserInterface userInterface) {

        return new CustomUserDetailsService(userInterface);
    }
}
