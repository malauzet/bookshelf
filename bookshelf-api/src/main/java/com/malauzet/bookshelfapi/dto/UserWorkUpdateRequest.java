package com.malauzet.bookshelfapi.dto;

import com.malauzet.bookshelfapi.model.ReadingStatus;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;

/**
 * Request body for {@code PATCH /api/users/{userId}/user-works/{id}} — the codebase's first
 * non-entity request DTO. Flattens every {@code UserWork} subtype's extra field into one record,
 * since a single JPA entity can't represent "maybe a {@code UserBook}, maybe a
 * {@code UserAudiobook}, ...". Every field is optional: only non-null values are applied, and a
 * field that doesn't apply to the resolved subtype (e.g. {@code currentPage} for a
 * {@code UserWebnovel}) is silently ignored by the controller rather than rejected.
 */
public record UserWorkUpdateRequest(
        ReadingStatus status,

        @Min(value = 1, message = "Rating must be between 1 and 10")
        @Max(value = 10, message = "Rating must be between 1 and 10")
        Integer rating,

        @Positive(message = "Current chapter must be greater than 0")
        Integer currentChapter,

        /** Applies only when the resolved {@code UserWork} is a {@code UserBook}/{@code UserManga}/{@code UserLightNovel}. */
        @Positive(message = "Current page must be greater than 0")
        Integer currentPage,

        /** Applies only when the resolved {@code UserWork} is a {@code UserAudiobook}. */
        @Positive(message = "Current minutes must be greater than 0")
        Integer currentMinutes
) {
}