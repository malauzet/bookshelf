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

/** An audiobook {@link Work}, optionally belonging to an {@link AudiobookSeries}. */
@Entity
@DiscriminatorValue("AUDIOBOOK")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Audiobook extends Work {

    /**
     * Current credited narrator; {@code null} if unknown. Like {@link Work#getAuthor()}, a
     * single overwritable value — doesn't track a re-recording with a different narrator.
     */
    private String narrator;

    /** Total runtime in whole minutes; {@code null} when unknown. Not a {@code Duration}/
     *  {@code LocalTime} — more precision than progress tracking needs in v1. */
    @Positive(message = "Total minutes must be greater than 0")
    private Integer totalMinutes;

    /**
     * Typed FK: can only reference an {@link AudiobookSeries}, never another format's series
     * table. {@code null} for a standalone audiobook. Read-only over the API — assigned
     * server-side via a {@code seriesId} query param on create/update, not deserialized from
     * nested JSON.
     */
    @ManyToOne
    @JoinColumn(name = "series_id")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private AudiobookSeries series;
}
