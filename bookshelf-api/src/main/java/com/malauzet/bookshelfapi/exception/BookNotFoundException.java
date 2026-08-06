package com.malauzet.bookshelfapi.exception;

/** Thrown when a {@code Book} id doesn't resolve; mapped to {@code 404} by {@link GlobalExceptionHandler}. */
public class BookNotFoundException extends RuntimeException {

    public BookNotFoundException(String message) {
        super(message);
    }
}
