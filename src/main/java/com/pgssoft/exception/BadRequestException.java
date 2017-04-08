package com.pgssoft.exception;

public class BadRequestException extends Exception {

    public BadRequestException(String reason) {
        super(reason);
    }
}
