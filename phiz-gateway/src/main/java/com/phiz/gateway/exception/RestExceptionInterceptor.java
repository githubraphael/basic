package com.phiz.gateway.exception;

import com.phiz.gateway.util.ExceptionUtil;
import com.phiz.gateway.util.FeignUtil;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionInterceptor {

    @Value("${spring.application.name}")
    private String applicationName;

    @ExceptionHandler({PhizException.class})
    public ResponseEntity<Error> handlePhizException(PhizException ex) {
        try {
            if (StringUtils.isEmpty(ex.getErrorSource())) {
                ex.setErrorSource(applicationName);
            }
            ex.printStackTrace();
            ResponseEntity<Error> responseEntity = ResponseEntity.status(ex.getResponseStatusCode())
                    .body(ExceptionUtil.buildError(ex));
            return responseEntity;
        } catch (Throwable th) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ExceptionUtil.getGenericError(th)
                    );
        }
    }


    @ExceptionHandler(FeignException.class)
    public ResponseEntity<Error> handleFeignStatusException(FeignException ex) {
        ex.printStackTrace();
        PhizException PhizException = FeignUtil.map(ex);
        return handlePhizException(PhizException);
    }
}
