package com.santander.apicartapp.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.santander.apicartapp.model.Role;

public interface RoleRepository extends CrudRepository<Role, Long> {
        Optional<Role> findByName(String name);
}
