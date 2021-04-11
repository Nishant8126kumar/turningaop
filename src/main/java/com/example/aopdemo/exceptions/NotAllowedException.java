package com.example.aopdemo.exceptions;

public class NotAllowedException extends RuntimeException {
    public NotAllowedException(String msg) {
        super(msg);
    }
}
