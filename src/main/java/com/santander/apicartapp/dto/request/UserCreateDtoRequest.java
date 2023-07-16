package com.santander.apicartapp.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreateDtoRequest {

    @NotEmpty(message = "The name cannot be empty")
    private String username;

    @NotEmpty(message = "The password cannot be empty")
    private String password;

    @NotEmpty(message = "The email cannot be empty")
    @Email(message = "The email is not valid")
    private String email;

}
