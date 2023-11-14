package com.driver.services.impl;

public class CustomerNotExistException extends RuntimeException {
    public CustomerNotExistException(String message) {
        super(message);
    }
}
