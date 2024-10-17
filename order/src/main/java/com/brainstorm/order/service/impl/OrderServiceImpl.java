package com.brainstorm.order.service.impl;

import com.brainstorm.order.dto.*;

import com.brainstorm.order.entity.EcomOrder;
import com.brainstorm.order.entity.OrderEntry;
import com.brainstorm.order.exception.ResourceNotFoundException;
import com.brainstorm.order.kafka.producer.MessageProducer;
import com.brainstorm.order.repository.OrderEntryRepository;
import com.brainstorm.order.repository.OrderRepository;
import com.brainstorm.order.service.IOrderService;
import com.brainstorm.order.service.client.CustomerFeignClient;
import com.brainstorm.order.service.client.ProductFeignClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements IOrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderEntryRepository orderEntryRepository;

    @Autowired
    MessageProducer producer;

    @Autowired
    ProductFeignClient productFeignClient;

    @Value("${spring.kafka.producer.enabled}")
    private String kafkaEnabled;

    @Value("${saga.enabled}")
    private String sagaPatternEnabled;

    @Autowired
    CustomerFeignClient customerFeignClient;

    private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);


    @Override
    public void createOrder(OrderDTO orderDTO) {
        logger.info("CreateOrder method to create the order");
        EcomOrder ecomOrder =  mapToOrder(orderDTO);
        if(orderDTO != null && orderDTO.getCustomerId() != null){
            ResponseEntity<CustomerDTO> customerDTOResponseEntity = customerFeignClient.fetchCustomerDetails(orderDTO.getCustomerId());
            if(customerDTOResponseEntity != null){
                CustomerDTO customerDTO = customerFeignClient.fetchCustomerDetails(orderDTO.getCustomerId()).getBody();
                ecomOrder.setCustomerId(customerDTO.getId());
            }
        }
        ecomOrder.setOrderEntryList(mapToOrderEntry(orderDTO.getOrderEntriesDTO()));
        EcomOrder ecomOrderTr =orderRepository.saveAndFlush(ecomOrder);
        logger.info("Save OrderEntry to DataBase OrderEntryId : {}", ecomOrderTr.getId());
//        ecomOrder.getOrderEntryList().forEach(orderEntryTr -> {
////            orderEntryTr.setOrder(ecomOrder);
//            orderEntryRepository.saveAndFlush(orderEntryTr);
//            logger.info("Save OrderEntry to DataBase OrderEntryId : {}", orderEntryTr.getId());
//        });

        if(kafkaEnabled.equals("true")){
            logger.info("Sending conformation message to Kafka");
            producer.sendMessage("order", "Order Id -> "+ecomOrder.getId() +" has been created and saved in DB ");
        }
        if(sagaPatternEnabled.equals("true")){
            OrderEvent orderEvent = new OrderEvent();
            orderEvent.setOrder(mapToOrderDTO(ecomOrder));
            orderEvent.setType("Order_Created");
            logger.info("Sending OrderEvent to Kafka Topic order to process the Payment service, Will be Consumed by Payment Service :: Order --> Payment");
            producer.sendMessage("order", orderEvent);
        }

    }

    @Override
    public OrderDTO fetchOrder(Long orderId) {
        logger.info("fetchOrder method to get the order {}",orderId);
        EcomOrder ecomOrder =  orderRepository.findById(orderId).orElseThrow(() ->new ResourceNotFoundException("Order", "OrderId", String.valueOf(orderId)));
        return mapToOrderDTO(ecomOrder);
    }

    @Override
    public void deleteOrder(Long orderId) {
        logger.info("deleteOrder method to delete the order {}",orderId);
        EcomOrder ecomOrder =  orderRepository.findById(orderId).orElseThrow(() ->new ResourceNotFoundException("Order", "OrderId", String.valueOf(orderId)));
        orderRepository.delete(ecomOrder);
    }

    public  EcomOrder mapToOrder(OrderDTO orderDTO){
        EcomOrder ecomOrder = new EcomOrder();
        ecomOrder.setStatus(orderDTO.getOrderStatus());
        ecomOrder.setCreatedAt(LocalDateTime.now());
//        ecomOrder.setOrderEntryList(mapToOrderEntry(orderDTO.getOrderEntriesDTO()));
        return ecomOrder;
    }

    public OrderDTO mapToOrderDTO(EcomOrder ecomOrder){
        ArrayList<OrderEntryDTO>  orderEntryDTOList = new ArrayList<>();
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderId(ecomOrder.getId());
        orderDTO.setOrderStatus(ecomOrder.getStatus());
        orderDTO.setCustomerId(ecomOrder.getCustomerId());
        ecomOrder.getOrderEntryList().forEach(orderEntry -> {
            OrderEntryDTO orderEntryDTO =  mapToOrderEntryDTO(orderEntry);
            orderEntryDTOList.add(orderEntryDTO);
        });
        orderDTO.setOrderEntriesDTO(orderEntryDTOList);
        return orderDTO;
    }

    public  List<OrderEntry> mapToOrderEntry(List<OrderEntryDTO> orderEntryListDTO){
        List<OrderEntry> orderEntryList = new ArrayList<>();

        orderEntryListDTO.forEach(orderEntryDTO -> {
                       OrderEntry orderEntry = new OrderEntry();
            orderEntry.setCreatedAt(LocalDateTime.now());
            orderEntry.setQuantity(orderEntryDTO.getQuantity());
            ProductDTO productDTO;
            if(orderEntryDTO.getProductDTO().getCode() != null){
                ResponseEntity<ProductDTO> productDTOResponseEntity = productFeignClient.fetchProduct(Long.parseLong(orderEntryDTO.getProductDTO().getCode()));
                if(productDTOResponseEntity.getBody() != null ){
                    productDTO =  productDTOResponseEntity.getBody();
                    orderEntry.setProductId(productDTO.getCode());
                    if(!productDTO.getPrice().equals(0.0)){
                        orderEntry.setPrice(productDTO.getPrice() * orderEntryDTO.getQuantity());
                    }
                }
            }
            OrderEntry orderEntryTr = orderEntryRepository.saveAndFlush(orderEntry);
            orderEntryList.add(orderEntryTr);
        });
        return orderEntryList;
    }

    public OrderEntryDTO mapToOrderEntryDTO(OrderEntry orderEntry){
        ResponseEntity<ProductDTO> productDTOResponseEntity = null;
        OrderEntryDTO orderEntryDTO = new OrderEntryDTO();
        orderEntryDTO.setId(orderEntry.getId());
        orderEntryDTO.setQuantity(orderEntry.getQuantity());
        ProductDTO productDTO= null;
        if(orderEntry.getProductId() != null){
            productDTOResponseEntity = productFeignClient.fetchProduct(Long.parseLong(orderEntry.getProductId()));
            if(productDTOResponseEntity != null){
                productDTO =  productDTOResponseEntity.getBody();
                orderEntryDTO.setPrice(productDTO.getPrice());
                orderEntryDTO.setProductDTO(productDTO);
                orderEntryDTO.setProductId(productDTO.getCode());
            }
        }
        return orderEntryDTO;
    }
}
