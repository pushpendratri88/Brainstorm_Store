package com.brainstorm.order.dto;

public class OrderEntryDTO {
    private Long id;
    private Double price;
    private ProductDTO productDTO;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public ProductDTO getProductDTO() {
        return productDTO;
    }

    public void setProductDTO(ProductDTO productDTO) {
        this.productDTO = productDTO;
    }

    public ProductDTO getEcomProductDTO() {
        return productDTO;
    }
}

