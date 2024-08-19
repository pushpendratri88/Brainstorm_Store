package com.brainstorm.product.mapper;


import com.brainstorm.product.dto.ProductDTO;
import com.brainstorm.product.entity.Product;
import com.brainstorm.product.repository.ProductRepository;

import java.time.LocalDateTime;

public class ProductMapper {
    public static Product mapToProduct(ProductDTO productDTO, ProductRepository productRepository){
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setCreatedAt(LocalDateTime.now());
        product.setCategory(productDTO.getCategory());
        product.setPrice(productDTO.getPrice());
        Product productTr = productRepository.saveAndFlush(product);
        return productTr;
    }


    public static ProductDTO mapToProductDTO(Product product){
        ProductDTO productDTO = new ProductDTO();
        productDTO.setCode(product.getId());
        productDTO.setName(product.getName());
        productDTO.setCategory(product.getCategory());
        productDTO.setPrice(product.getPrice());
        productDTO.setCreatedAt(product.getCreatedAt());
        return productDTO;
    }
}
