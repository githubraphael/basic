package com.phiz.userservice.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class LoginResponse implements Serializable {

    private static final long serialVersionUID = 2491030044405166375L;

    private String token;


}