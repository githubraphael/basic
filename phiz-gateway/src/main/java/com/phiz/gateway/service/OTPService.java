package com.phiz.gateway.service;

import com.phiz.gateway.dto.otp.OtpRequest;
import com.phiz.gateway.dto.otp.OtpResponse;

public interface OTPService {

    OtpResponse generateOtp(OtpRequest request);

    OtpResponse validateOtp(OtpRequest request);
}
