package com.example.anish.prodapp.exception;

public class ResourceNotFoundException extends RuntimeException {
   public ResourceNotFoundException(String msg){
        super(msg);
    }
}
