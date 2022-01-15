package com.phiz.gateway.config;

import com.phiz.gateway.exception.PhizException;
import com.phiz.gateway.util.FeignUtil;
import feign.FeignException;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;

public class FeignConfiguration {


    @Bean
    public ErrorDecoder errorDecoder() {
        return (methodKey, response) -> {
            ErrorDecoder defaultErrorDecoder = new ErrorDecoder.Default();
            Exception exception = defaultErrorDecoder.decode(methodKey, response);
            if (exception instanceof PhizException) {
                throw PhizException.class.cast(exception);
            }
            if (exception instanceof FeignException) {
                FeignException fe = FeignException.class.cast(exception);
                throw FeignUtil.map(fe);
            }
            return defaultErrorDecoder.decode(methodKey, response);
        };
    }
}

