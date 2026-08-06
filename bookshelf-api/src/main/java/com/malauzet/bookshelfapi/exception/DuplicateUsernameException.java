package com.malauzet.bookshelfapi.exception;

/** Thrown on registration if the username is already taken; mapped to {@code 409} by {@link GlobalExceptionHandler}. */
public class DuplicateUsernameException extends RuntimeException {

    public DuplicateUsernameException(String message) {
        super(message);
    }
}
