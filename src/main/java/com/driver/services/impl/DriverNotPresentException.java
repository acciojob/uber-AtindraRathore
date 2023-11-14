package com.driver.services.impl;

public class DriverNotPresentException extends RuntimeException {
    public DriverNotPresentException(String message) {
        super(message);
    }
}
