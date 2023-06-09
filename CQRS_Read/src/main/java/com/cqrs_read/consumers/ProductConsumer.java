package com.cqrs_read.consumers;


import com.cqrs_read.repositories.ProductRepository;
import com.product_event.model.Product;
import com.product_event.model.ProductEvent;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ProductConsumer {

    @Autowired
    private ProductRepository productRepository;
    @KafkaListener(topics = "${spring.kafka.topic.name}" , groupId = "${spring.kafka.consumer.group-id}")
    private void productConsumers(ProductEvent productEvent)
    {
        ModelMapper mapper = new ModelMapper();
        Product product_event= productEvent.getProduct();
        com.cqrs_read.entities.Product product_read = mapper.map(product_event, com.cqrs_read.entities.Product.class);
        productRepository.save(product_read);
    }
}
