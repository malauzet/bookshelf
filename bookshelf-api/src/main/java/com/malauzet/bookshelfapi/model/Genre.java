package com.malauzet.bookshelfapi.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Closed, code-controlled genre vocabulary for a {@link Work} (orthogonal to format — a
 * {@code Manga} can be {@code FANTASY} just as easily as a {@code Book} can). Persisted via
 * {@code @Enumerated(EnumType.STRING)} on {@code Work.genres}, so renaming or removing a constant
 * already in use requires an explicit data migration (an {@code UPDATE} before the code change
 * ships), not just a recompile — {@code STRING} storage only protects against reordering, not
 * renaming. Adding a new constant is always safe.
 */
@Getter
@RequiredArgsConstructor
public enum Genre {
    FANTASY("Fantasy"),
    SCIENCE_FICTION("Science Fiction"),
    ROMANCE("Romance"),
    HORROR("Horror"),
    THRILLER("Thriller"),
    MYSTERY("Mystery"),
    CRIME("Crime"),
    ACTION("Action"),
    ADVENTURE("Adventure"),
    DRAMA("Drama"),
    COMEDY("Comedy"),
    SLICE_OF_LIFE("Slice of Life"),
    HISTORICAL("Historical"),
    DYSTOPIAN("Dystopian"),
    POST_APOCALYPTIC("Post-Apocalyptic"),
    PSYCHOLOGICAL("Psychological"),
    SUPERNATURAL("Supernatural"),
    PARANORMAL("Paranormal"),
    ISEKAI("Isekai"),
    XIANXIA("Xianxia"),
    WUXIA("Wuxia"),
    URBAN_FANTASY("Urban Fantasy"),
    STEAMPUNK("Steampunk"),
    CYBERPUNK("Cyberpunk"),
    MECHA("Mecha"),
    SPORTS("Sports"),
    MILITARY("Military"),
    POLITICAL("Political"),
    LEGAL("Legal"),
    BIOGRAPHY("Biography"),
    AUTOBIOGRAPHY("Autobiography"),
    ESSAY("Essay"),
    POETRY("Poetry"),
    CHILDREN("Children"),
    YOUNG_ADULT("Young Adult"),
    NEW_ADULT("New Adult"),
    EROTICA("Erotica"),
    HAREM("Harem"),
    REVERSE_HAREM("Reverse Harem"),
    TRAGEDY("Tragedy"),
    WAR("War"),
    SURVIVAL("Survival"),
    MARTIAL_ARTS("Martial Arts"),
    GAME("Game"),
    SYSTEM("System"),
    REINCARNATION("Reincarnation"),
    TIME_TRAVEL("Time Travel"),
    CONSPIRACY("Conspiracy"),
    SCHOOL_LIFE("School Life"),
    COMPETITION("Competition"),
    LITRPG("LitRPG");

    /** Human-readable label for UI display; the enum constant itself is what's persisted. */
    private final String displayName;
}