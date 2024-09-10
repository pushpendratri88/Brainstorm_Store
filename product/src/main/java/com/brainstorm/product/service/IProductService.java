package com.brainstorm.product.service;


import com.brainstorm.product.dto.ProductDTO;

import java.util.List;

public interface IProductService {
    void addProducts(ProductDTO productDTO);

    ProductDTO fetchProduct(Long productId);

    List<ProductDTO> fetchAllProduct();

    void deleteProduct(Long productId);

}

