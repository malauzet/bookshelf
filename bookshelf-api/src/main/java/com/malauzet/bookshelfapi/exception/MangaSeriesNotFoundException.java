package com.malauzet.bookshelfapi.exception;

/** Thrown when a {@code MangaSeries} id doesn't resolve; mapped to {@code 404} by {@link GlobalExceptionHandler}. */
public class MangaSeriesNotFoundException extends RuntimeException {

    public MangaSeriesNotFoundException(String message) {
        super(message);
    }
}
