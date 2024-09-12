package com.brainstorm.order.fallback;

import com.brainstorm.order.dto.ProductDTO;
import com.brainstorm.order.service.client.ProductFeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class ProductFallback implements ProductFeignClient {
    @Override
    public ResponseEntity<ProductDTO> fetchProduct(Long productId) {
        return null;
    }

    @Override
    public ResponseEntity<List<ProductDTO>> fetchAllProduct() {
        return null;
    }
}
