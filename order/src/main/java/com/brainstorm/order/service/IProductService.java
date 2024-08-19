package com.brainstorm.order.service;

import com.brainstorm.order.dto.ProductDTO;

import java.util.List;

public interface IProductService {
    void addProducts(ProductDTO productDTO);

    ProductDTO fetchProduct(String productId);

    List<ProductDTO> fetchAllProduct();

    void deleteProduct(String productId);

}

