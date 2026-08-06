package com.malauzet.bookshelfapi.exception;

/**
 * Thrown when a {@code UserWork} id doesn't resolve, or resolves but doesn't belong to the
 * requesting user (see {@code UserWorkController.getOwnedUserWork}, which masks that distinction
 * deliberately). Mapped to {@code 404} by {@link GlobalExceptionHandler}.
 */
public class UserWorkNotFoundException extends RuntimeException {

    public UserWorkNotFoundException(String message) {
        super(message);
    }
}
