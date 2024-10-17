package com.brainstorm.stock.kafka.producer;

import com.brainstorm.stock.dto.DeliveryEvent;
import com.brainstorm.stock.dto.PaymentEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class MessageProducer {
    @Autowired
    KafkaTemplate<String,Object> kafkaTemplate;

    public void sendMessage(String topic, Object object){
        if(object instanceof String message){
            kafkaTemplate.send(topic,message);
        }
        else if(object instanceof PaymentEvent paymentEvent){
            kafkaTemplate.send(topic,paymentEvent);
        }
        else if(object instanceof DeliveryEvent deliveryEvent){
            kafkaTemplate.send(topic,deliveryEvent);
        }
    }
}
