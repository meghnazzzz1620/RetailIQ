package com.retailiq.auth;

import com.retailiq.entity.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private Role role;

}