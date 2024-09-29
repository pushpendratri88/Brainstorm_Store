package com.brainstorm.payment.kafka.producer;

import com.brainstorm.payment.dto.OrderEvent;
import com.brainstorm.payment.dto.PaymentEvent;
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
        else if(object instanceof PaymentEvent paymentEvent){
            kafkaTemplate.send(topic,paymentEvent);
        }
        else if(object instanceof OrderEvent orderEvent){
            kafkaTemplate.send(topic,orderEvent);
        }

    }
}
