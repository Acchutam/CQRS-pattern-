package com.cqrs_write.publishers;



import com.product_event.model.ProductEvent;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class ProductPublisher {

    @Autowired
    private NewTopic newTopic;

    @Autowired
    private KafkaTemplate<String, ProductEvent> kafkaTemplate;

    public void sendMessage(ProductEvent productEvent){
        Message<ProductEvent> message = MessageBuilder
                                        .withPayload(productEvent)
                                        .setHeader(KafkaHeaders.TOPIC,newTopic.name())
                                        .build();
        kafkaTemplate.send(message);
    }
}
