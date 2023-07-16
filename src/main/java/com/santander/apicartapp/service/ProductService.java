package com.santander.apicartapp.service;

import java.util.List;

import com.santander.apicartapp.model.Product;

public interface ProductService {
    public List<Product> findAll();

    public Product save(Product product);

    public Product findById(Long id);

    public Product update(Product product, Long id);

    public void delete(Long id);
}
