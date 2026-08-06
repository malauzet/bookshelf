package com.malauzet.bookshelfapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** A light novel {@link Work}, optionally belonging to a {@link LightNovelSeries}. */
@Entity
@DiscriminatorValue("LIGHT_NOVEL")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LightNovel extends Work {

    /**
     * Current credited illustrator, separate from {@link Work#getAuthor()} (the story writer).
     * Like {@code author}, a single overwritable value — doesn't track a mid-series artist
     * change.
     */
    private String artist;

    /** {@code null} when unknown (e.g. edition not yet catalogued in detail). */
    @Positive(message = "Total pages must be greater than 0")
    private Integer totalPages;

    /**
     * Typed FK: can only reference a {@link LightNovelSeries}, never another format's series
     * table. {@code null} for a standalone light novel. Read-only over the API — assigned
     * server-side via a {@code seriesId} query param on create/update, not deserialized from
     * nested JSON.
     */
    @ManyToOne
    @JoinColumn(name = "series_id")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LightNovelSeries series;
}
