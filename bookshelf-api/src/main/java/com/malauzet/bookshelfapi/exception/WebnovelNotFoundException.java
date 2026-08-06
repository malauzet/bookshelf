package com.malauzet.bookshelfapi.exception;

/** Thrown when a {@code Webnovel} id doesn't resolve; mapped to {@code 404} by {@link GlobalExceptionHandler}. */
public class WebnovelNotFoundException extends RuntimeException {

    public WebnovelNotFoundException(String message) {
        super(message);
    }
}
