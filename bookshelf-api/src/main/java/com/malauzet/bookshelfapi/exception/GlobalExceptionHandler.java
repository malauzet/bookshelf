package com.malauzet.bookshelfapi.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Central mapping from exceptions to structured {@link ErrorResponse} JSON bodies, so callers get
 * a consistent {@code 4xx}/{@code 5xx} shape instead of raw stack traces. Covers every custom
 * "not found"/"duplicate" exception, Bean Validation failures, DB constraint violations, and a
 * catch-all fallback for anything unexpected.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException e) {
        ErrorResponse error = new ErrorResponse(e.getMessage(), null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(BookSeriesNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleBookSeriesNotFoundException(BookSeriesNotFoundException e) {
        ErrorResponse error = new ErrorResponse(e.getMessage(), null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(WebnovelSeriesNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleWebnovelSeriesNotFoundException(WebnovelSeriesNotFoundException e) {
        ErrorResponse error = new ErrorResponse(e.getMessage(), null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(LightNovelSeriesNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleLightNovelSeriesNotFoundException(LightNovelSeriesNotFoundException e) {
        ErrorResponse error = new ErrorResponse(e.getMessage(), null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(MangaSeriesNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleMangaSeriesNotFoundException(MangaSeriesNotFoundException e) {
        ErrorResponse error = new ErrorResponse(e.getMessage(), null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(AudiobookSeriesNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleAudiobookSeriesNotFoundException(AudiobookSeriesNotFoundException e) {
        ErrorResponse error = new ErrorResponse(e.getMessage(), null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleBookNotFoundException(BookNotFoundException e) {
        ErrorResponse error = new ErrorResponse(e.getMessage(), null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(WebnovelNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleWebnovelNotFoundException(WebnovelNotFoundException e) {
        ErrorResponse error = new ErrorResponse(e.getMessage(), null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(LightNovelNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleLightNovelNotFoundException(LightNovelNotFoundException e) {
        ErrorResponse error = new ErrorResponse(e.getMessage(), null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(MangaNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleMangaNotFoundException(MangaNotFoundException e) {
        ErrorResponse error = new ErrorResponse(e.getMessage(), null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(AudiobookNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleAudiobookNotFoundException(AudiobookNotFoundException e) {
        ErrorResponse error = new ErrorResponse(e.getMessage(), null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(UserWorkNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserWorkNotFoundException(UserWorkNotFoundException e) {
        ErrorResponse error = new ErrorResponse(e.getMessage(), null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(DuplicateUsernameException.class)
    public ResponseEntity<ErrorResponse> handleDuplicateUsernameException(DuplicateUsernameException e) {
        ErrorResponse error = new ErrorResponse(e.getMessage(), null);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    @ExceptionHandler(DuplicateTrackingException.class)
    public ResponseEntity<ErrorResponse> handleDuplicateTrackingException(DuplicateTrackingException e) {
        ErrorResponse error = new ErrorResponse(e.getMessage(), null);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    /** Collects every {@code @Valid} field failure into one response, rather than only the first. */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException e) {
        Map<String, String> fieldErrors = new HashMap<>();
        for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
            fieldErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        ErrorResponse error = new ErrorResponse("Validation failed", fieldErrors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    /**
     * Distinguishes a unique-constraint violation ({@code 23505}) from a foreign-key-restrict
     * violation ({@code 23503}) using the ANSI-standard SQLSTATE code rather than DB-specific
     * error text, so this stays correct across the planned PostgreSQL migration (currently H2).
     */
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleDataIntegrityViolationException(DataIntegrityViolationException e) {

        String sqlState = null;

        Throwable cause = e.getMostSpecificCause();

        if (cause instanceof SQLException sqlException) {
            sqlState = sqlException.getSQLState();
        }

        String message = "23503".equals(sqlState)
                ? "Cannot complete this operation: other records still depend on this resource"
                : "A record with these unique constraints already exists";

        ErrorResponse error = new ErrorResponse(message, null);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    /** Fallback for anything not handled above — returns a structured body instead of a raw 500. */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleUnexpectedException(Exception e) {
        ErrorResponse error = new ErrorResponse("An unexpected error occurred", null);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}
