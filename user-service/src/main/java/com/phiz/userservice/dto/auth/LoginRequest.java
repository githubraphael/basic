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
public class LoginRequest implements Serializable {

    private static final long serialVersionUID = -7391547477066340023L;

    private String username;
    private String password;
    private String newPassword;

}
