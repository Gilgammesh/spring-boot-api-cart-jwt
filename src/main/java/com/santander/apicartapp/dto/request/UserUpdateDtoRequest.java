package com.santander.apicartapp.dto.request;

import jakarta.validation.constraints.Email;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdateDtoRequest {

    private String username;

    @Email(message = "The email is not valid")
    private String email;

}
