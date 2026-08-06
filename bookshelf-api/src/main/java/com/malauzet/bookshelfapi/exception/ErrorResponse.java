package com.malauzet.bookshelfapi.exception;

import java.util.Map;

/**
 * Uniform JSON error body returned by every {@link GlobalExceptionHandler} handler.
 *
 * @param message    human-readable summary of what went wrong
 * @param fieldError per-field validation messages, keyed by field name; {@code null} unless the
 *                   error came from a {@code MethodArgumentNotValidException}
 */
public record ErrorResponse(String message, Map<String, String> fieldError) {
}
