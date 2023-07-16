package com.santander.apicartapp.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.santander.apicartapp.dto.mapper.UserDtoMapper;
import com.santander.apicartapp.dto.request.UserCreateDtoRequest;
import com.santander.apicartapp.dto.request.UserUpdateDtoRequest;
import com.santander.apicartapp.dto.response.UserDtoResponse;
import com.santander.apicartapp.exception.BadRequestException;
import com.santander.apicartapp.exception.ResourceNotFoundException;
import com.santander.apicartapp.model.Role;
import com.santander.apicartapp.model.User;
import com.santander.apicartapp.repository.RoleRepository;
import com.santander.apicartapp.repository.UserRepository;
import com.santander.apicartapp.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional(readOnly = true)
    public List<UserDtoResponse> findAll() {
        List<User> users = (List<User>) userRepository.findAll();
        return users.stream().map(u -> UserDtoMapper.builder().setUser(u).build()).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Page<UserDtoResponse> findAll(Pageable pageable) {
        return userRepository
                .findAll(pageable)
                .map(u -> UserDtoMapper.builder().setUser(u).build());
    }

    @Override
    public UserDtoResponse save(UserCreateDtoRequest userCreate) {
        User user = new User();
        user.setUsername(userCreate.getUsername());
        user.setPassword(passwordEncoder.encode(userCreate.getPassword()));
        user.setEmail(userCreate.getEmail());
        Optional<Role> currentRole = roleRepository.findByName("ROLE_USER");
        List<Role> roles = new ArrayList<>();
        if (currentRole.isPresent()) {
            roles.add(currentRole.orElseThrow());
        }
        user.setRoles(roles);
        User savedUser = userRepository.save(user);
        return UserDtoMapper.builder().setUser(savedUser).build();

    }

    @Override
    @Transactional(readOnly = true)
    public UserDtoResponse findById(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            throw new ResourceNotFoundException("User not found");
        }
        return UserDtoMapper.builder().setUser(user).build();
    }

    @Override
    public UserDtoResponse update(UserUpdateDtoRequest userUpdate, Long id) {
        User currentUser = userRepository.findById(id).orElse(null);
        if (currentUser == null) {
            throw new BadRequestException("Invalid user");
        }
        if (userUpdate.getUsername() != null) {
            currentUser.setUsername(userUpdate.getUsername());
        }
        if (userUpdate.getEmail() != null) {
            currentUser.setEmail(userUpdate.getEmail());
        }
        userRepository.save(currentUser);
        return UserDtoMapper.builder().setUser(currentUser).build();
    }

    @Override
    public void delete(Long id) {
        User currentUser = userRepository.findById(id).orElse(null);
        if (currentUser == null) {
            throw new BadRequestException("Invalid user");
        }
        userRepository.deleteById(id);
    }

}
