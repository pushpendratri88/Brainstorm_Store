package com.brainstorm.order.exception;

public class ProductAlreadyExistsException extends RuntimeException{
    public ProductAlreadyExistsException(String message){
        super(message);

    }
}
