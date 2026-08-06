package com.malauzet.bookshelfapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/** A printed or ebook {@link Work}, optionally belonging to a {@link BookSeries}. */
@Entity
@DiscriminatorValue("BOOK")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Book extends Work {

    /** {@code null} when unknown (e.g. edition not yet catalogued in detail). */
    @Positive(message = "Total pages must be greater than 0")
    private Integer totalPages;

    /**
     * Typed FK: can only reference a {@link BookSeries}, never another format's series table.
     * {@code null} for a standalone book. Read-only over the API — assigned server-side via a
     * {@code seriesId} query param on create/update, not deserialized from nested JSON.
     */
    @ManyToOne
    @JoinColumn(name = "series_id")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private BookSeries series;
}