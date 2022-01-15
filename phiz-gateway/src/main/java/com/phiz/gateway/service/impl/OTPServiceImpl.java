package com.phiz.gateway.service.impl;


import com.phiz.gateway.client.OtpClient;
import com.phiz.gateway.dto.otp.OtpRequest;
import com.phiz.gateway.dto.otp.OtpResponse;
import com.phiz.gateway.service.OTPService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OTPServiceImpl implements OTPService {

    private final OtpClient otpClient;

    public OTPServiceImpl(OtpClient otpClient) {

        this.otpClient = otpClient;
    }

    @Override
    public OtpResponse generateOtp(OtpRequest request) {

        return otpClient.generateOtp(request);
    }

    @Override
    public OtpResponse validateOtp(OtpRequest request) {
        return otpClient.validateOTP(request);
    }
}
