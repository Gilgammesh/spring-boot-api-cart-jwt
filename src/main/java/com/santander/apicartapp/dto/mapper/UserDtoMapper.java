package com.santander.apicartapp.dto.mapper;

import com.santander.apicartapp.dto.response.UserDtoResponse;
import com.santander.apicartapp.model.User;

public class UserDtoMapper {

    private User user;

    private UserDtoMapper() {
    }

    public static UserDtoMapper builder() {
        return new UserDtoMapper();
    }

    public UserDtoMapper setUser(User user) {
        this.user = user;
        return this;
    }

    public UserDtoResponse build() {
        if (user == null) {
            throw new RuntimeException("The entity user must pass!");
        }
        return new UserDtoResponse(this.user.getId(), user.getUsername(), user.getEmail());
    }

}
