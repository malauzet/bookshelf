package com.malauzet.bookshelfapi.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * A {@link UserWork}'s reading state — a property of the user's relationship to a work, not of
 * the work itself (the same work can be {@code FINISHED} for one user and {@code PLAN_TO_READ}
 * for another). Persisted via {@code @Enumerated(EnumType.STRING)}.
 */
@Getter
@RequiredArgsConstructor
public enum ReadingStatus {
    READING("Reading"),
    HIATUS("Hiatus"),
    DROPPED("Dropped"),
    PLAN_TO_READ("Plan to Read"),
    FINISHED("Finished");

    /** Human-readable label for UI display; the enum constant itself is what's persisted. */
    private final String displayName;
}
