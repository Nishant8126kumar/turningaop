package com.example.aopdemo.exceptions;

public class MongoDbException extends RuntimeException {
    public MongoDbException(String msg) {
        super(msg);
    }
}
