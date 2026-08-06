package com.malauzet.bookshelfapi.exception;

public class AudiobookSeriesNotFoundException extends RuntimeException {

    public AudiobookSeriesNotFoundException(String message) {
        super(message);
    }
}
