package com.phiz.otpservice.service;

public interface OTPService {

    Boolean generateOtp(String key);

    Boolean validateOTP(String key, Integer otpNumber);
}
