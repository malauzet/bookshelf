package com.malauzet.bookshelfapi.exception;

public class MangaNotFoundException extends RuntimeException {

    public MangaNotFoundException(String message) {
        super(message);
    }
}
