package com.phiz.gateway.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Device implements Serializable {

    private static final long serialVersionUID = 345094529991293329L;

    private String apiVersion;
    private String model;
    private String osVersion;
    private String serial;
}
