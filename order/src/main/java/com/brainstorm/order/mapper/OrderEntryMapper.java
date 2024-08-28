package com.brainstorm.order.mapper;

import com.brainstorm.order.dto.CustomerDTO;
import com.brainstorm.order.dto.OrderEntryDTO;
import com.brainstorm.order.dto.ProductDTO;
import com.brainstorm.order.entity.OrderEntry;
import com.brainstorm.order.exception.ResourceNotFoundException;
import com.brainstorm.order.repository.OrderEntryRepository;
//import com.brainstorm.order.repository.ProductRepository;
import com.brainstorm.order.service.impl.CustomerServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderEntryMapper {
    private static final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

//    public static List<OrderEntry> mapToOrderEntry(List<OrderEntryDTO> orderEntryListDTO,   ProductRepository productRepository,  OrderEntryRepository orderEntryRepository){
//        List<OrderEntry> orderEntryList = new ArrayList<>();
//
//        orderEntryListDTO.forEach(orderEntryDTO -> {
//            ProductDTO productDTO =  null;
//            String ProductServiceurl = productServiceUrl + "/fetchProduct?input=" + orderEntryDTO.getProductId();
//            logger.info("Requesting Product details from URL: ", ProductServiceurl);
//                try {
//                    productDTO = restTemplate.getForObject(ProductServiceurl, ProductDTO.class);
//                } catch (Exception e) {
//                    logger.error("Error while requesting customer details", e);
//                    throw e;
//                }
//            OrderEntry orderEntry = new OrderEntry();
//            orderEntry.setCreatedAt(LocalDateTime.now());
//            orderEntry.setQuantity(orderEntryDTO.getQuantity());
//            orderEntry.setPrice(productDTO.getPrice() * orderEntryDTO.getQuantity());
//            orderEntry.setProduct(productRepository.findById(productDTO.getCode()).orElseThrow(()-> new ResourceNotFoundException("ProductEntity","Product name ","Product value ")));
//            OrderEntry orderEntryTr = orderEntryRepository.saveAndFlush(orderEntry);
//            orderEntryList.add(orderEntryTr);
//        });
//        return orderEntryList;
//    }
//
//
//    public static OrderEntryDTO mapToOrderEntryDTO(OrderEntry orderEntry){
//        OrderEntryDTO orderEntryDTO = new OrderEntryDTO();
//        orderEntryDTO.setId(orderEntry.getId());
//        orderEntryDTO.setQuantity(orderEntry.getQuantity());
//        orderEntryDTO.setPrice(orderEntry.getProduct().getPrice() * orderEntry.getQuantity());
//        orderEntryDTO.setProductDTO(ProductMapper.mapToProductDTO(orderEntry.getProduct()));
//        orderEntryDTO.setProductId(orderEntry.getProduct().getId());
//        return orderEntryDTO;
//    }
}
