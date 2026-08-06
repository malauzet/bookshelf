package com.malauzet.bookshelfapi.exception;

/** Thrown when a {@code User} id doesn't resolve; mapped to {@code 404} by {@link GlobalExceptionHandler}. */
public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String message) {
        super(message);
    }
}
