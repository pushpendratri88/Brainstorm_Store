package com.brainstorm.order.mapper;

import com.brainstorm.order.dto.ProductDTO;
import com.brainstorm.order.entity.Product;

public class ProductMapper {
    public static Product mapToProduct(ProductDTO productDTO){
        Product product = new Product();
        product.setProductName(product.getProductName());
        return product;
    }

    public static ProductDTO mapToProductDTO(Product product, ProductDTO productDTO ){
        productDTO.setProductName(product.getProductName());
        return productDTO;
    }
}
