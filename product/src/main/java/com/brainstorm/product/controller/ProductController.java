package com.brainstorm.product.controller;


import com.brainstorm.product.dto.ProductContactInfoDto;
import com.brainstorm.product.dto.ProductDTO;
import com.brainstorm.product.dto.ResponseDTO;
import com.brainstorm.product.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/products")
public class ProductController {

    @Autowired
    IProductService productService;

    @Value("${build.version}")
    private String buildVersion;

    @Autowired
    private Environment environment;

    @Autowired
    private ProductContactInfoDto productContactInfoDto;

    @PostMapping(value = "/addProducts")
    public ResponseEntity<ResponseDTO> addProducts(@RequestBody List<ProductDTO> productDTOList){
        productDTOList.forEach(productDTO -> {
            productService.addProducts(productDTO);
        });
        return  ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDTO("201", "Products has been created successfully"));
    }

    @PostMapping(value = "/addProduct")
    public ResponseEntity<ResponseDTO> addProduct(@RequestBody ProductDTO productDTO){
        productService.addProducts(productDTO);
        return  ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDTO("201", "Product has been created successfully"));
    }

    @GetMapping(value = "/fetchProduct")
    public ResponseEntity<ProductDTO> fetchProduct(@RequestParam Long productId){
        ProductDTO productDTO = productService.fetchProduct(productId);
        return  ResponseEntity.status(HttpStatus.OK).body(productDTO);
    }
    @GetMapping(value = "/fetchAllProduct")
    public ResponseEntity<List<ProductDTO>> fetchAllProduct(){
        List<ProductDTO> productDTOList = productService.fetchAllProduct();
        return  ResponseEntity.status(HttpStatus.OK).body(productDTOList);
    }

    @PostMapping(value = "/deleteProduct")
    public ResponseEntity<ResponseDTO> deleteProduct(@RequestParam Long productId){
        productService.deleteProduct(productId);
        return  ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO("201", "Product has been deleted successfully"));

    }

    @GetMapping("/build-info")
    public ResponseEntity<String> getBuildInfo() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(buildVersion);
    }
    @GetMapping("/java-version")
    public ResponseEntity<String> getJavaVersion() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(environment.getProperty("JAVA_HOME"));
    }
    @GetMapping("/contact-info")
    public ResponseEntity<ProductContactInfoDto> getContactInfo() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productContactInfoDto);
    }
}
