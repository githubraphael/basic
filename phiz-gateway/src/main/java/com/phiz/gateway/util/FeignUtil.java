package com.phiz.gateway.util;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.phiz.gateway.exception.Error;
import com.phiz.gateway.exception.PhizException;
import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;

import java.util.function.Supplier;

public class FeignUtil {

    public static String getErrorMessage(FeignException fe) {
        String error = fe.contentUTF8();
        if (StringUtils.isEmpty(error)) {
            error = fe.getMessage();
        }
        return error;
    }

    public static HttpStatus getHttpStatus(FeignException fe) {
        HttpStatus httpstatus = HttpStatus.resolve(fe.status());
        httpstatus = httpstatus == null ? HttpStatus.INTERNAL_SERVER_ERROR : httpstatus;
        return httpstatus;
    }

    //TODO get rid of it
    public static Object resultOrException1(Supplier c) {
        try {
            return c.get();
        } catch (FeignException e) {
            throw map(e);
        }
    }

    //TODO get rid of it
    public static Object resultOrException(ResponseEntity<?> responseEntity) {
        if (responseEntity == null) {
            throw new PhizException.Builder("Null Result Received").build();
        } else if (responseEntity.getStatusCodeValue() >= 200 && responseEntity.getStatusCodeValue() < 300) {
            // Call was successful. Return body
            return responseEntity.getBody();
        } else {
            throw new PhizException.Builder(responseEntity.getBody().toString()).build();
        }
    }

    public static Error buildError(FeignException fe) {
        try {
            String responseString = fe.contentUTF8();
            if (!StringUtils.isEmpty(responseString)) {
                return new ObjectMapper().readValue(responseString, Error.class);
            }
            return Error.builder().errorMessage(fe.getMessage()).build();
        } catch (Exception e) {
            return ExceptionUtil.getGenericError(e);
        }
    }

    public static PhizException map(FeignException fe) {
        Error error = buildError(fe);
        return new PhizException.Builder(error.getErrorMessage())
                .withStatus(FeignUtil.getHttpStatus(fe))
                .fromException(fe)
                .forId(error.getResourceId())
                .errorSource(error.getErrorSource())
                .errCode(error.getErrorCode())
                .build();
    }
}
