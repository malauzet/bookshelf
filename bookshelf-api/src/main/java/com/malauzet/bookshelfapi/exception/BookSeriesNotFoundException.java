package com.malauzet.bookshelfapi.exception;

/** Thrown when a {@code BookSeries} id doesn't resolve; mapped to {@code 404} by {@link GlobalExceptionHandler}. */
public class BookSeriesNotFoundException extends RuntimeException {

    public BookSeriesNotFoundException(String message) {
        super(message);
    }
}
