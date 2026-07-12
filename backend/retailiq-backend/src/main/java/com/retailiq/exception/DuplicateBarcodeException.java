package com.retailiq.exception;

public class DuplicateBarcodeException extends BusinessException {

    public DuplicateBarcodeException(String message) {
        super(message);
    }
}