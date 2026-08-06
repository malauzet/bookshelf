package com.malauzet.bookshelfapi.exception;

/** Thrown when a {@code WebnovelSeries} id doesn't resolve; mapped to {@code 404} by {@link GlobalExceptionHandler}. */
public class WebnovelSeriesNotFoundException extends RuntimeException {

    public WebnovelSeriesNotFoundException(String message) {
        super(message);
    }
}
