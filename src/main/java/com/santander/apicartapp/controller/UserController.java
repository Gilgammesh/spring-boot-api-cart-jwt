package com.santander.apicartapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.santander.apicartapp.dto.request.UserCreateDtoRequest;
import com.santander.apicartapp.dto.request.UserUpdateDtoRequest;
import com.santander.apicartapp.dto.response.UserDtoResponse;
import com.santander.apicartapp.service.UserService;
import com.santander.apicartapp.util.ValidationUtil;

import jakarta.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDtoResponse>> findAll() {
        List<UserDtoResponse> users = userService.findAll();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/page/{page}")
    public ResponseEntity<Page<UserDtoResponse>> findAll(@PathVariable Integer page) {
        Pageable pageable = PageRequest.of(page, 5);
        Page<UserDtoResponse> users = userService.findAll(pageable);
        return ResponseEntity.ok(users);

    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody UserCreateDtoRequest user, BindingResult result) {
        ValidationUtil.handleValidationErrors(result);
        userService.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDtoResponse> findById(@PathVariable Long id) {
        UserDtoResponse user = userService.findById(id);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@Valid @RequestBody UserUpdateDtoRequest user, BindingResult result,
            @PathVariable Long id) {
        ValidationUtil.handleValidationErrors(result);
        userService.update(user, id);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
