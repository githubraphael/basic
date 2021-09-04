package com.phiz.basic.service;

public interface SMSUtilityService {

    Boolean sendSms(String mobileNumber, Integer code);
}
