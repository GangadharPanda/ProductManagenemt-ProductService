package com.ps.service.Exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductNotFoundException extends RuntimeException{
    private String errorCode;
    public ProductNotFoundException(String errorCode , String message){
        super(message);
        this.errorCode = errorCode;
    }
}
