package com.brainstorm.order.kafka.producer;

import com.brainstorm.order.dto.OrderEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;



@Component
public class MessageProducer {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public void sendMessage(String topic, Object object) {
        if(object instanceof String str){
            kafkaTemplate.send(topic, str);
        }
        else if(object instanceof OrderEvent orderEvent){
            kafkaTemplate.send(topic,orderEvent);
        }

    }


}
