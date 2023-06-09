package com.cqrs_write.services.impl;


import com.cqrs_write.entities.Product;
import com.cqrs_write.publishers.ProductPublisher;
import com.cqrs_write.repositories.ProductRepository;
import com.cqrs_write.services.ProductService;


import com.product_event.model.ProductEvent;
import org.modelmapper.ModelMapper;
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
        ModelMapper mapper = new ModelMapper();
        com.product_event.model.Product product_event = mapper.map(product, com.product_event.model.Product.class);
        ProductEvent productEvent = new ProductEvent("Product Created",product_event);
        productPublisher.sendMessage(productEvent);
        return productRepository.save(product);
    }
}
