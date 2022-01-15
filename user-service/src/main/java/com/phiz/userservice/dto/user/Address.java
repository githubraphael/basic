package com.phiz.userservice.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Address implements Serializable {

    private static final long serialVersionUID = -778964787682767897L;

    protected Integer areaId;
    protected Integer cityId;
    protected String cityName;
    protected Integer countryId;
    protected Integer provinceId;
    protected String address;
    protected String location;
    protected double latitude;
    protected double longitude;
}
