package com.phiz.gateway.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class User implements Serializable {

    private static final long serialVersionUID = -227144171253981751L;

    private String id;
    private String username;
    private String password;
    private String phoneNumber;
    private String email;
    private LocalDate birthday;
    private String sex;
    private Name name;
    private Address address;
    private Device device;
    private String resetToken;

}
