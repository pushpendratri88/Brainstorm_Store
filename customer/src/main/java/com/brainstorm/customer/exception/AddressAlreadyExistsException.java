package com.brainstorm.customer.exception;

public class AddressAlreadyExistsException extends RuntimeException{
    public AddressAlreadyExistsException(String message){
        super(message);

    }
}
