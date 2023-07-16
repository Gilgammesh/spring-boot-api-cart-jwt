package com.santander.apicartapp.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDtoResponse {
    private Long id;
    private String username;
    private String email;

    public UserDtoResponse(Long id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
    }

    public UserDtoResponse() {
    }
}
