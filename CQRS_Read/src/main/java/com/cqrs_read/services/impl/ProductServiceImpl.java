package com.cqrs_read.services.impl;

import com.cqrs_read.entities.Product;
import com.cqrs_read.repositories.ProductRepository;
import com.cqrs_read.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }
}
