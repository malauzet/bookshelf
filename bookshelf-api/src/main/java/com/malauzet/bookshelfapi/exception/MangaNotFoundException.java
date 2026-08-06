package com.malauzet.bookshelfapi.exception;

/** Thrown when a {@code Manga} id doesn't resolve; mapped to {@code 404} by {@link GlobalExceptionHandler}. */
public class MangaNotFoundException extends RuntimeException {

    public MangaNotFoundException(String message) {
        super(message);
    }
}
