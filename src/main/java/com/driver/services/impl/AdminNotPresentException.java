package com.driver.services.impl;

public class AdminNotPresentException extends RuntimeException{
    public AdminNotPresentException(String errorMessage){
        super(errorMessage);
    }
}
