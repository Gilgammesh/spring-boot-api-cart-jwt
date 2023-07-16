package com.santander.apicartapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.santander.apicartapp.exception.BadRequestException;
import com.santander.apicartapp.exception.ResourceNotFoundException;
import com.santander.apicartapp.model.Product;
import com.santander.apicartapp.repository.ProductRepository;
import com.santander.apicartapp.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Product> findAll() {
        return (List<Product>) productRepository.findAll();
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    @Transactional(readOnly = true)
    public Product findById(Long id) {
        Product product = productRepository.findById(id).orElse(null);
        if (product == null) {
            throw new ResourceNotFoundException("Product not found");
        }
        return product;
    }

    @Override
    public Product update(Product product, Long id) {
        Product currentProduct = productRepository.findById(id).orElse(null);
        if (currentProduct == null) {
            throw new BadRequestException("Invalid product");
        }
        if (product.getName() != null) {
            currentProduct.setName(product.getName());
        }
        if (product.getDescription() != null) {
            currentProduct.setDescription(product.getDescription());
        }
        if (product.getPrice() != null) {
            currentProduct.setPrice(product.getPrice());
        }
        productRepository.save(currentProduct);
        return currentProduct;
    }

    @Override
    public void delete(Long id) {
        Product currentProduct = productRepository.findById(id).orElse(null);
        if (currentProduct == null) {
            throw new BadRequestException("Invalid product");
        }
        productRepository.deleteById(id);
    }

}
