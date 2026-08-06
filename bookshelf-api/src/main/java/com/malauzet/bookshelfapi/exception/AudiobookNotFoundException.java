package com.malauzet.bookshelfapi.exception;

public class AudiobookNotFoundException extends RuntimeException {

    public AudiobookNotFoundException(String message) {
        super(message);
    }
}
