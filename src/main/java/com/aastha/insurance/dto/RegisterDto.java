package com.aastha.insurance.dto;

import lombok.Data;

import java.util.Date;

@Data
public class RegisterDto {

    private String userName;
    private String password;
    private String phone;
    private String email;
    private String city;
    private String state;
    private Date dob;
}

