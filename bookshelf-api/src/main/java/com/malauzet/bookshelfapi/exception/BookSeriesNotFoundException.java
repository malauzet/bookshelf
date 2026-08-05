package com.malauzet.bookshelfapi.exception;

public class BookSeriesNotFoundException extends RuntimeException {

    public BookSeriesNotFoundException(String message) {
        super(message);
    }
}
