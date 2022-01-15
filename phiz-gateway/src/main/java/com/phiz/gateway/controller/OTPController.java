package com.phiz.gateway.controller;

import com.phiz.gateway.dto.otp.OtpRequest;
import com.phiz.gateway.dto.otp.OtpResponse;
import com.phiz.gateway.service.OTPService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "api/otp")
public class OTPController {

    private final OTPService otpService;

    public OTPController(OTPService otpService) {
        this.otpService = otpService;
    }

    @PostMapping(value = "/generate")
    OtpResponse generateOtp(@RequestBody OtpRequest request) {

        return otpService.generateOtp(request);
    }

    @PostMapping(value = "/validate")
    OtpResponse validateOTP(@RequestBody OtpRequest request) {

        return otpService.validateOtp(request);
    }
}
