package com.phiz.basic.service;

public interface OTPUtilityService {

    Integer generateOTP(String key);

    Integer getOPTByKey(String key);

    void clearOTPFromCache(String key);

}
