package com.brainstorm.product.service.impl;


import com.brainstorm.product.dto.ProductDTO;
import com.brainstorm.product.entity.Product;
import com.brainstorm.product.exception.ProductAlreadyExistsException;
import com.brainstorm.product.mapper.ProductMapper;
import com.brainstorm.product.repository.ProductRepository;
import com.brainstorm.product.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    ProductRepository productRepository;

    @Override
    public void addProducts(ProductDTO productDTO) {
        Product product =  null;
        if(productDTO.getCode() != null){
            Optional<Product> optionalProduct =  productRepository.findById(productDTO.getCode());
            if(!optionalProduct.isPresent()){
                product = ProductMapper.mapToProduct(productDTO,productRepository);
            }
            else{
                throw new ProductAlreadyExistsException("Product Already Exist : " + product.getId());
            }
        }
        else{
            product = ProductMapper.mapToProduct(productDTO,productRepository);
        }
    }

    @Override
    public ProductDTO fetchProduct(String productId) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if(optionalProduct.isPresent()){
            return ProductMapper.mapToProductDTO(optionalProduct.get());
        }
        return null;
    }

    @Override
    public List<ProductDTO> fetchAllProduct() {
        List<Product> productentityList = productRepository.findAll();
        List<ProductDTO> productDTOS =  new ArrayList<>();
        productentityList.forEach(product -> {
            ProductDTO productDTO = ProductMapper.mapToProductDTO(product);
            productDTOS.add(productDTO);
        });
        return productDTOS;
    }

    @Override
    public void deleteProduct(String productId) {
        productRepository.deleteById(productId);
    }
}
