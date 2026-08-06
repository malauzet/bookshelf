package com.malauzet.bookshelfapi.exception;

/** Thrown when an {@code Audiobook} id doesn't resolve; mapped to {@code 404} by {@link GlobalExceptionHandler}. */
public class AudiobookNotFoundException extends RuntimeException {

    public AudiobookNotFoundException(String message) {
        super(message);
    }
}
