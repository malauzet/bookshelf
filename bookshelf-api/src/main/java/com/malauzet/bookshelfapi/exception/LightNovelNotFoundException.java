package com.malauzet.bookshelfapi.exception;

/** Thrown when a {@code LightNovel} id doesn't resolve; mapped to {@code 404} by {@link GlobalExceptionHandler}. */
public class LightNovelNotFoundException extends RuntimeException {

    public LightNovelNotFoundException(String message) {
        super(message);
    }
}
