package com.phiz.gateway.util;

import com.phiz.gateway.exception.Error;
import com.phiz.gateway.exception.PhizException;

public class ExceptionUtil {

    public static Error getGenericError(Throwable th) {
        th.printStackTrace();
        return Error.builder()
                .errorMessage("Some unknown error occured and unable to map to right error.")
                .build();
    }

    public static Error buildError(PhizException ke) {
        return Error.builder()
                .errorMessage(ke.getErrorMsg())
                .errorCode(ke.getErrCode())
                .resourceId(ke.getId())
                .errorSource(ke.getErrorSource())
                .debugMessage(getDebugMessage(ke))
                .build();
    }

    private static String getDebugMessage(Throwable th) {
        if (th.getCause() != null && th.getCause() != th) {
            return getDebugMessage(th.getCause());
        }
        return th.getMessage();
    }
}