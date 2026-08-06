package com.malauzet.bookshelfapi.exception;

public class MangaSeriesNotFoundException extends RuntimeException {

    public MangaSeriesNotFoundException(String message) {
        super(message);
    }
}
