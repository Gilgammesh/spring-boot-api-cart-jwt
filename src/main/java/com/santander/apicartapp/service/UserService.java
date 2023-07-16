package com.santander.apicartapp.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.santander.apicartapp.dto.request.UserCreateDtoRequest;
import com.santander.apicartapp.dto.request.UserUpdateDtoRequest;
import com.santander.apicartapp.dto.response.UserDtoResponse;

public interface UserService {
    public List<UserDtoResponse> findAll();

    public Page<UserDtoResponse> findAll(Pageable pageable);

    public UserDtoResponse save(UserCreateDtoRequest user);

    public UserDtoResponse findById(Long id);

    public UserDtoResponse update(UserUpdateDtoRequest user, Long id);

    public void delete(Long id);

}
