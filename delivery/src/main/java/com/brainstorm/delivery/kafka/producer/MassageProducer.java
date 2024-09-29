package com.brainstorm.delivery.kafka.producer;

import com.brainstorm.delivery.dto.DeliveryEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class MassageProducer {
    @Autowired
    KafkaTemplate<String,Object> kafkaTemplate;

    public void sendMessage(String topic, Object object){
        if(object instanceof String message){
            kafkaTemplate.send(topic,message);
        }
        else if(object instanceof DeliveryEvent deliveryEvent){
            kafkaTemplate.send(topic,deliveryEvent);
        }
    }
}
