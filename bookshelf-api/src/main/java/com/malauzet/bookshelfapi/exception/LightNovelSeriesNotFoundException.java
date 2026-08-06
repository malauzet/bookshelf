package com.malauzet.bookshelfapi.exception;

/** Thrown when a {@code LightNovelSeries} id doesn't resolve; mapped to {@code 404} by {@link GlobalExceptionHandler}. */
public class LightNovelSeriesNotFoundException extends RuntimeException {

    public LightNovelSeriesNotFoundException(String message) {
        super(message);
    }
}
