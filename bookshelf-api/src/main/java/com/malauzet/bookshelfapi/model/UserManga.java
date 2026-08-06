package com.malauzet.bookshelfapi.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** Tracking state for a {@link User}'s progress through a {@link Manga}. */
@Entity
@DiscriminatorValue("MANGA")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserManga extends UserWork {

    /** {@code null} until the user starts reading; not cross-checked against {@code totalPages}. */
    @Positive(message = "Current page must be greater than 0")
    private Integer currentPage;
}
