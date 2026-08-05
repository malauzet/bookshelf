package com.malauzet.bookshelfapi.dto;

import com.malauzet.bookshelfapi.model.ReadingStatus;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

public record UserWorkUpdateRequest(
        ReadingStatus status,

        @Min(value = 1, message = "Rating must be between 1 and 10")
        @Max(value = 10, message = "Rating must be between 1 and 10")
        Integer rating,

        Integer currentChapter,
        Integer currentPage,
        Integer currentMinutes
) {
}