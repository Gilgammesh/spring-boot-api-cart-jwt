package com.santander.apicartapp.repository;

import org.springframework.data.repository.CrudRepository;

import com.santander.apicartapp.model.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {

}
