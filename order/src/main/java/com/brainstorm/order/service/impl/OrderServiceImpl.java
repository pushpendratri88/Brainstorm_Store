package com.brainstorm.order.service.impl;

import com.brainstorm.order.dto.OrderDTO;

import com.brainstorm.order.dto.OrderEntryDTO;
import com.brainstorm.order.dto.ProductDTO;
import com.brainstorm.order.entity.EcomOrder;
import com.brainstorm.order.entity.OrderEntry;
import com.brainstorm.order.exception.ResourceNotFoundException;
import com.brainstorm.order.kafka.producer.MessageProducer;
import com.brainstorm.order.repository.OrderEntryRepository;
import com.brainstorm.order.repository.OrderRepository;

import com.brainstorm.order.service.CustomerService;
import com.brainstorm.order.service.IOrderService;
import com.brainstorm.order.service.client.CustomerFeignClient;
import com.brainstorm.order.service.client.ProductFeignClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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
    CustomerService customerService;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    MessageProducer producer;

    @Autowired
    ProductFeignClient productFeignClient;

    @Value("${spring.kafka.producer.enabled}")
    private String kafkaEnabled;

    @Autowired
    CircuitBreakerFactory circuitBreakerFactory;

    @Value("${product.service.url}")
    private String productServiceUrl;

    private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);


    @Override
    public void createOrder(OrderDTO orderDTO) {
        EcomOrder ecomOrder =  mapToOrder(orderDTO);
        if(orderDTO != null && orderDTO.getCustomerId() != null
        && customerService != null){
            String customerId = customerService.getCustomer(orderDTO.getCustomerId()).getId();
            ecomOrder.setCustomerId(customerId);
        }
        orderRepository.saveAndFlush(ecomOrder);
        ecomOrder.getOrderEntryList().forEach(orderEntryTr -> {
            orderEntryTr.setOrder(ecomOrder);
            orderEntryRepository.saveAndFlush(orderEntryTr);
        });

        if(kafkaEnabled.equals("true")){
            producer.sendMessage("order", "Order Id -> "+ecomOrder.getId() +" has been created and saved in DB ");
        }
    }

    @Override
    public OrderDTO fetchOrder(Long orderId) {
        EcomOrder ecomOrder =  orderRepository.findById(orderId).orElseThrow(() ->new ResourceNotFoundException("Order", "OrderId", String.valueOf(orderId)));
        return mapToOrderDTO(ecomOrder,new OrderDTO());
    }

    @Override
    public void deleteOrder(Long orderId) {
        EcomOrder ecomOrder =  orderRepository.findById(orderId).orElseThrow(() ->new ResourceNotFoundException("Order", "OrderId", String.valueOf(orderId)));
        orderRepository.delete(ecomOrder);
    }

    public  EcomOrder mapToOrder(OrderDTO orderDTO){
        EcomOrder ecomOrder = new EcomOrder();
        ecomOrder.setStatus(orderDTO.getOrderStatus());
        ecomOrder.setCreatedAt(LocalDateTime.now());
        ecomOrder.setOrderEntryList(mapToOrderEntry(orderDTO.getOrderEntriesDTO()));
        return ecomOrder;
    }

    public OrderDTO mapToOrderDTO(EcomOrder ecomOrder, OrderDTO orderDTO ){
        ArrayList<OrderEntryDTO>  orderEntryDTOList = new ArrayList<>();
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
            ProductDTO productDTO = getProductDTOFromProductService(orderEntryDTO.getProductId());
            OrderEntry orderEntry = new OrderEntry();
            orderEntry.setCreatedAt(LocalDateTime.now());
            orderEntry.setQuantity(orderEntryDTO.getQuantity());
            orderEntry.setProductId(productDTO.getCode());
            if(!productDTO.getPrice().equals(0.0)){
                orderEntry.setPrice(productDTO.getPrice() * orderEntryDTO.getQuantity());
            }
            OrderEntry orderEntryTr = orderEntryRepository.saveAndFlush(orderEntry);
            orderEntryList.add(orderEntryTr);
        });
        return orderEntryList;
    }

    public OrderEntryDTO mapToOrderEntryDTO(OrderEntry orderEntry){
        ProductDTO productDTO = getProductDTOFromProductService(orderEntry.getProductId());
        OrderEntryDTO orderEntryDTO = new OrderEntryDTO();
        orderEntryDTO.setId(orderEntry.getId());
        orderEntryDTO.setQuantity(orderEntry.getQuantity());
        orderEntryDTO.setPrice(productDTO.getPrice() * orderEntry.getQuantity());
        orderEntryDTO.setProductDTO(productDTO);
        orderEntryDTO.setProductId(productDTO.getCode());
        return orderEntryDTO;
    }

    private ProductDTO getProductDTOFromProductService(String productId) {

        String ProductServiceurl = productServiceUrl + "/fetchProduct?productId=" + productId;
        logger.info("Requesting Product details from URL: ", ProductServiceurl);

        CircuitBreaker circuitBreaker = circuitBreakerFactory.create("productService");
        return circuitBreaker.run(() -> productFeignClient.fetchProduct(Long.parseLong(productId)).getBody(),throwable -> {
            logger.error("Product service is down, returning fallback response", throwable);
            return fallbackForProductService(productId);
        });
    }

    private ProductDTO fallbackForProductService(String productId) {
        return new ProductDTO(productId,"Unknown",LocalDateTime.now() );
    }
}
