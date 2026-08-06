package com.malauzet.bookshelfapi.exception;

/**
 * Thrown when a user tries to track a work they're already tracking, mirroring the DB's
 * {@code UNIQUE(user_id, work_id)} constraint at the application level. Mapped to {@code 409} by
 * {@link GlobalExceptionHandler}.
 */
public class DuplicateTrackingException extends RuntimeException {

    public DuplicateTrackingException(String message) {
        super(message);
    }
}
