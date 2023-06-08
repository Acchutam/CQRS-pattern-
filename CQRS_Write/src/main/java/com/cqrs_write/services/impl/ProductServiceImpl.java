package com.cqrs_write.services.impl;


import com.cqrs_write.entities.Product;

import com.cqrs_write.models.ProductEvent;
import com.cqrs_write.publishers.ProductPublisher;
import com.cqrs_write.repositories.ProductRepository;
import com.cqrs_write.services.ProductService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductPublisher productPublisher;

    @Override
    public Product save(Product product) {
        ProductEvent productEvent = new ProductEvent("Product Created",product);
        productPublisher.sendMessage(productEvent);
        return productRepository.save(product);
    }
}
