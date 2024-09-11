package com.brainstorm.order.service.client;

import com.brainstorm.order.dto.ProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("product")
public interface ProductFeignClient {

    @GetMapping(value = "/api/products/fetchProduct")
    ResponseEntity<ProductDTO> fetchProduct(@RequestParam Long productId);

    @GetMapping(value = "/api/products/fetchAllProduct")
    ResponseEntity<List<ProductDTO>> fetchAllProduct();
    
}
