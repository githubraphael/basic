package com.phiz.gateway.client;

import com.phiz.gateway.dto.otp.OtpRequest;
import com.phiz.gateway.dto.otp.OtpResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "${client.otp.name}", url = "${client.otp.url}")
public interface OtpClient {

    @PostMapping(value = "/generate")
    OtpResponse generateOtp(@RequestBody OtpRequest request);

    @PostMapping(value = "/validate")
    OtpResponse validateOTP(@RequestBody OtpRequest request);
}
