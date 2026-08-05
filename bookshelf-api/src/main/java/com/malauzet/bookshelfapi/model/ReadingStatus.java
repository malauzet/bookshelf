package com.malauzet.bookshelfapi.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ReadingStatus {
    READING("Reading"),
    HIATUS("Hiatus"),
    DROPPED("Dropped"),
    PLAN_TO_READ("Plan to Read"),
    FINISHED("Finished");

    private final String displayName;
}
