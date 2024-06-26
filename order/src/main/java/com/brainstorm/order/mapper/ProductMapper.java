package com.brainstorm.order.mapper;

import com.brainstorm.order.dto.ProductDTO;
import com.brainstorm.order.entity.Product;

import java.time.LocalDateTime;

public class ProductMapper {
    public static Product mapToProduct(ProductDTO productDTO){
        Product product = new Product();
        product.setProductName(productDTO.getProductName());
        product.setCreatedAt(LocalDateTime.now());
        return product;
    }

    public static ProductDTO mapToProductDTO(Product product){
        ProductDTO productDTO = new ProductDTO();
        productDTO.setProductCode(product.getId());
        productDTO.setProductName(product.getProductName());
        return productDTO;
    }
}
