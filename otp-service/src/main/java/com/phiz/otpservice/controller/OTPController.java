package com.phiz.otpservice.controller;

import com.phiz.otpservice.dto.OtpRequest;
import com.phiz.otpservice.dto.OtpResponse;
import com.phiz.otpservice.service.OTPService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = OTPController.OTP_BASE_PATH)
public class OTPController {

    public static final String OTP_BASE_PATH = "/otp";
    private OTPService otpService;

    public OTPController(OTPService otpService) {
        this.otpService = otpService;
    }

    @PostMapping(value = "/generate", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OtpResponse> generateOTP(@RequestBody OtpRequest request) {

        Boolean isGenerated = otpService.generateOtp(request.getPhoneNumber());

        if (!isGenerated) {
            return new ResponseEntity<>(OtpResponse.builder()
                    .status("Error")
                    .message("OTP can not be generated")
                    .build(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(OtpResponse.builder()
                .status("Success")
                .message("OTP successfully generated. Please check your mobile phone")
                .build(), HttpStatus.OK);
    }

    @PostMapping(value = "validate", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OtpResponse> validateOTP(@RequestBody OtpRequest request) {

        boolean isValid = otpService.validateOTP(request.getPhoneNumber(), Integer.valueOf(request.getCode()));

        if (!isValid) {


            return new ResponseEntity<>(OtpResponse.builder()
                    .status("Error")
                    .message("OTP is not valid")
                    .build(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(OtpResponse.builder()
                .status("Success")
                .message("OTP is valid")
                .build(), HttpStatus.OK);
    }
}
