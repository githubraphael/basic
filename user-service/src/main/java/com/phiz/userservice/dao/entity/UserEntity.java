package com.phiz.userservice.dao.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.phiz.userservice.dto.user.Address;
import com.phiz.userservice.dto.user.Device;
import com.phiz.userservice.dto.user.Name;
import lombok.*;
import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.time.LocalDate;

@Data
@Builder
@Document(collection = "user")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"userName"})
public class UserEntity {

    @Id
    private String id;

    @Indexed(unique = true)
    private String username;

    @JsonIgnore
    private String password;

    @Indexed(unique = true)
    private String phoneNumber;

    @Indexed(unique = true)
    private String email;

    private LocalDate birthday;
    private String sex;
    private String userType;
    private String resetToken;

    private Name name;
    private Address address;
    private Device device;

    @CreatedDate
    private Instant created;

    @LastModifiedDate
    private Instant modified;

    @CreatedBy
    private String createdBy;

    @LastModifiedBy
    private String lastModifiedBy;


}
