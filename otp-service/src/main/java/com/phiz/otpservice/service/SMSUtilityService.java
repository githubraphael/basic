package com.phiz.otpservice.service;

public interface SMSUtilityService {

    Boolean sendSms(String mobileNumber, Integer code);
}
