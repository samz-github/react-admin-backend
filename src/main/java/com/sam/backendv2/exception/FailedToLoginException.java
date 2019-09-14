package com.sam.backendv2.exception;

public class FailedToLoginException extends Exception {

    public FailedToLoginException(String username) {
        super(username);
    }
}
