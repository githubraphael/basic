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
public class Name implements Serializable {

    private static final long serialVersionUID = 5391452553593196103L;

    private String firstName;
    private String lastName;
    private String middleName;
    private String nickname;
}
