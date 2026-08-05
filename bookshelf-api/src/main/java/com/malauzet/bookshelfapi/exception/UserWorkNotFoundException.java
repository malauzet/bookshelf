package com.malauzet.bookshelfapi.exception;

public class UserWorkNotFoundException extends RuntimeException {

    public UserWorkNotFoundException(String message) {
        super(message);
    }
}
