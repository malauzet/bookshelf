package com.malauzet.bookshelfapi.exception;

/** Thrown when an {@code AudiobookSeries} id doesn't resolve; mapped to {@code 404} by {@link GlobalExceptionHandler}. */
public class AudiobookSeriesNotFoundException extends RuntimeException {

    public AudiobookSeriesNotFoundException(String message) {
        super(message);
    }
}
