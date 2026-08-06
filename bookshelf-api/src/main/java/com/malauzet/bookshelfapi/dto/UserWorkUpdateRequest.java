package com.malauzet.bookshelfapi.dto;

import com.malauzet.bookshelfapi.model.ReadingStatus;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;

public record UserWorkUpdateRequest(
        ReadingStatus status,

        @Min(value = 1, message = "Rating must be between 1 and 10")
        @Max(value = 10, message = "Rating must be between 1 and 10")
        Integer rating,

        @Positive(message = "Current chapter must be greater than 0")
        Integer currentChapter,

        @Positive(message = "Current page must be greater than 0")
        Integer currentPage,

        @Positive(message = "Current minutes must be greater than 0")
        Integer currentMinutes
) {
}