package com.malauzet.bookshelfapi.exception;

public class DuplicateTrackingException extends RuntimeException {

    public DuplicateTrackingException(String message) {
        super(message);
    }
}
